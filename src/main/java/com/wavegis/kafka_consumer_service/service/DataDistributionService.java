package com.wavegis.kafka_consumer_service.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wavegis.kafka_consumer_service.config.NtouConfig;
import com.wavegis.kafka_consumer_service.kafka.KafkaDTO;
import com.wavegis.kafka_consumer_service.kafka.KafkaRainDTO;
import com.wavegis.kafka_consumer_service.model.dto.IowSensorListDTO;
import com.wavegis.kafka_consumer_service.model.dto.NtouDevicesDTO;
import com.wavegis.kafka_consumer_service.model.enums.PublisherEnum;
import com.wavegis.kafka_consumer_service.model.vo.RainGaugeVO;
import com.wavegis.kafka_consumer_service.model.vo.StationStatusPostVO;
import com.wavegis.kafka_consumer_service.model.vo.WaterGaugePostVO;
import com.wavegis.kafka_consumer_service.util.JsonConverter;

@Service
public class DataDistributionService {

    private static final Logger logger = LoggerFactory.getLogger(DataDistributionService.class);

    @Autowired
    private IowPublisherApiService iowPublisherApiService;

    @Autowired
    private NtouPublisherApiService ntouPublisherApiService;

    @Autowired
    private TpeSewerService tpeSewerService;

    @Autowired
    private KaohsiungWrbService kaohsiungWrbService;

    @Autowired
    private ChanghuaService changhuaService;

    @Autowired
    private NewTaipeiService newTaipeiService;

    @Autowired
    private NewTaipeiSewerService newTaipeiSewerService;

    private Map<String, List<IowSensorListDTO>> iowSensorDtoMap = IowPublisherApiService.iowSensorDtoMap;

    private Map<String, List<NtouDevicesDTO>> ntouDevicesDtoMap = NtouPublisherApiService.ntouDevicesDtoMap;

    private Function<String, KafkaDTO> prepareDto;

    private Function<String, KafkaRainDTO> prepareRainDto;

    private ExecutorService executor;

    // @Autowired
    // private FilterService filterService;

    @Autowired
    private NtouFilterService ntouFilterService;

    @Autowired
    private NtouToFengChiaService ntouToFengChiaService;

    // @Autowired
    // private ChanghuaWatercenterService changhuaWatercenterService;

    @Autowired
    private NtouConfig ntouConfig;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @PostConstruct
    private void init() {

        // 初始化連接池數量
        executor = Executors.newFixedThreadPool(4);

        // 將資料處理和 API 呼叫抽成方法，減少重複碼
        prepareDto = (kafkaMessage) -> {
            KafkaDTO dto = new KafkaDTO();
            String[] strs = kafkaMessage.split(",");
            dto.setStrs(strs);
            return dto;
        };

        prepareRainDto = (kafkaMessage) -> {
            KafkaRainDTO dto = new KafkaRainDTO();
            String[] strs = kafkaMessage.split(",");
            dto.setStrs(strs);
            return dto;
        };
    }

    public void distribution(String topices, PublisherEnum publisherEnum, String org_id, String st_no,
            String kafka_message) {
              switch (publisherEnum) {

            case ntouToFengChia: {

                // 組VO
                KafkaDTO dto = prepareDto.apply(kafka_message);
                String stationCodeName = ntouConfig.getFloodStnos().containsKey(st_no)
                        ? ntouConfig.getFloodStnos().get(st_no)
                        : ntouConfig.getWaterStnos().containsKey(st_no) ? ntouConfig.getWaterStnos().get(st_no)
                                : ntouConfig.getRainStnos().getOrDefault(st_no, null);

                if (stationCodeName == null) {
                    break;
                }
                // 時間格式轉換
                String timeStr = dto.getDatatime();
                LocalDateTime localDateTime = LocalDateTime.parse(timeStr, formatter);
                ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("Asia/Taipei"));
                Instant instant = zonedDateTime.toInstant();
                String stationTime = instant.toString();


                boolean isNetworkFailed = false;

                StationStatusPostVO vo = new StationStatusPostVO(stationCodeName, stationTime, isNetworkFailed);

                boolean isRain = ntouFilterService.isNtouRainStation(st_no);
                boolean isFlood = ntouFilterService.isNtouFloodStation(st_no);
                boolean isWater = ntouFilterService.isNtouWaterStation(st_no);

                if (isFlood || isWater) {

                    WaterGaugePostVO waterGauge = new WaterGaugePostVO(st_no, dto.getWaterInner());
                    vo.setWaterGaugeArray(Collections.singletonList(waterGauge));
                } else if (isRain) {
                    RainGaugeVO rainGauge = new RainGaugeVO(st_no, dto.getRain());
                    vo.setRainGauge(rainGauge);
                } else {
                    // logger.error("[FILTER] 此站不在南投丟逢甲的sensor名單中 st_no={}，略過處理", st_no);

                    break;
                }
                String message = ntouToFengChiaService.postData(vo);
                logger.info("ntouToFengChia---topics={},message={},data={}",topices,message,JsonConverter.convert(vo));
                break;
            }
            default: {
                logger.warn("upload type={} not impl", publisherEnum.name());
                break;
            }
        }
    }

}
