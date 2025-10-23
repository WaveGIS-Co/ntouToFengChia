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
import com.wavegis.kafka_consumer_service.model.dto.FloodValueAllDTO;
import com.wavegis.kafka_consumer_service.model.dto.IowSensorListDTO;
import com.wavegis.kafka_consumer_service.model.dto.NtouDevicesDTO;
import com.wavegis.kafka_consumer_service.model.dto.RainDataDTO;
import com.wavegis.kafka_consumer_service.model.enums.PublisherEnum;
import com.wavegis.kafka_consumer_service.model.vo.ChsewerPostVO;
import com.wavegis.kafka_consumer_service.model.vo.ChuploadPostVO;
import com.wavegis.kafka_consumer_service.model.vo.IowPublisherPostVO;
import com.wavegis.kafka_consumer_service.model.vo.KaohsiungWrbPublisherFloodPostVO;
import com.wavegis.kafka_consumer_service.model.vo.KaohsiungWrbPublisherSewerPostVO;
import com.wavegis.kafka_consumer_service.model.vo.NtouPublisherPostVO;
import com.wavegis.kafka_consumer_service.model.vo.RainGaugeVO;
import com.wavegis.kafka_consumer_service.model.vo.StationStatusPostVO;
import com.wavegis.kafka_consumer_service.model.vo.TpeSewerPublisherPostVO;
import com.wavegis.kafka_consumer_service.model.vo.WaterGaugePostVO;
import com.wavegis.kafka_consumer_service.util.JsonConverter;
import com.wavegis.kafka_consumer_service.util.Util;

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

    @Autowired
    private FilterService filterService;

    @Autowired
    private NtouFilterService ntouFilterService;

    @Autowired
    private NtouToFengChiaService ntouToFengChiaService;

    @Autowired
    private ChanghuaWatercenterService changhuaWatercenterService;

    @Autowired
    private NtouConfig ntouConfig;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final String floodStNos = "00109flood019033,00109flood019038,00109flood019021,00109flood019036,00109flood019006,00109flood019017,00109flood019022"
            + ",00109flood019005,00109flood019012,00109flood019011,00109flood019027,00109flood019043,0000000011110050"
            + ",0000000011110036,0000000011110016,00109flood019015,00109flood019016,00109flood019019,00109flood019042"
            + ",00109flood019026,00109flood019030,00109flood019010,00109flood019004,0000000011110031,00109flood019034"
            + ",00109flood019037,00109flood019035,00109flood019039,0000000011110042,0000000011110044,00109flood019024"
            + ",00109flood019013,00109flood019018,0000000011110028,0000000011110033,00109flood019001,00109flood019031"
            + ",0000000011110040,00109flood019014,0000000011110027,00109flood019020,00109flood019025,0000000011110051"
            + ",00109flood019003,00109flood019007,0000000011110022,0000000011110021,0000000011110049,0000000011110025"
            + ",0000000011110026,00109flood019009,00109flood019008,0000000011110038,00109flood019023,00109flood019045"
            + ",0000000011110029,00109flood019044,00109flood019040,00109flood019032,00109flood019029,00109flood019028"
            + ",00109flood019002,0000000011110043,0000000011110047,0000000011110037,0000000011110039,0000000011110030"
            + ",0000000011110023,0000000011110048,00109flood019041";

    private final String waterStNos = "0000000011110010,0000000011110011,0000000011110024,0000000011110032,0000000011110034,0000000011110035"
            + ",0000000011110046,000000wra0400001,000000wra0400002,000000wra0400003,000000wra0400004"
            + ",00109water019002,00109water019003,00109water019004,00109water019005,00109water019006,00109water019007"
            + ",00109water019008,00109water019009,00109water019010"
            + ",A34-TPQ1,A36-WGQ1,A37-KCPS,A38-SYQ1,A39-SGYH,A40-HXQ1,A41-GH000022,A42-GH000020,A43-GH000021"
            + ",WG_P_W_00392,WG_RR_W_00061,WG_RR_W_00062,WG_RR_W_00063,WG_RR_W_00064,WG_RR_W_00065,WG_RR_W_00066,WG_RR_W_00067"
            + ",WG_RR_W_00068,WG_RR_W_00069,WG_RR_W_00070,WG_RR_W_00071,WG_RR_W_00072,WG_RR_W_00073,WG_RR_W_00074,WG_RR_W_00075"
            + ",WG_RR_W_00076,WG_RR_W_00078,WG_RR_W_00079,WG_RR_W_00080,WG_RR_W_00081,WG_RR_W_00082,WG_RR_W_00083,WG_RR_W_00084"
            + ",WG_RR_W_00085,WG_RR_W_00086,WG_RR_W_00087,WG_RR_W_00089,WG_RR_W_00090,WG_RR_W_00094,WG_RR_W_00096,WG_RR_W_00097"
            + ",WG_RR_W_001000,WG_RR_W_00101,WG_RR_W_00102,WG_RR_W_00103,WG_RR_W_00104,WG_RR_W_00105,WG_RR_W_00106,WG_RR_W_00107"
            + ",WG_RR_W_00108,WG_RR_W_00109,WG_RR_W_00110,WG_RR_W_00111,WG_RR_W_00112,WG_RR_W_00113,WG_RR_W_00114,WG_RR_W_00115"
            + ",WG_RR_W_00116,WG_RR_W_00117";

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
        logger.info("[DEBUG] distribution called with publisherEnum={}, st_no={}, org_id={}", publisherEnum, st_no,
                org_id);
        switch (publisherEnum) {
            case iow: {
                if (iowSensorDtoMap.containsKey(st_no)) {
                    KafkaDTO dto = prepareDto.apply(kafka_message);
                    int resCode = iowPublisherApiService.postData(st_no,
                            Collections.singletonList(Util.toVo(dto, new IowPublisherPostVO())));
                    logger.info("Iow---topics={}, resCode={}, st_no={}, datatime={}, water_inner={}",
                            topices, resCode, st_no, dto.getDatatime(), dto.getWaterInner());
                }
                break;
            }
            case ntou: {
                if (ntouDevicesDtoMap.containsKey(st_no)) {
                    KafkaDTO dto = prepareDto.apply(kafka_message);
                    int resCode = ntouPublisherApiService.postData(st_no,
                            Collections.singletonList(Util.toVo(dto, new NtouPublisherPostVO())));
                    // int resCode = 200;
                    logger.info("Ntou---topics={}, resCode={}, st_no={}, datatime={}, water_inner_bed={}, rain={}",
                            topices, resCode, st_no, dto.getDatatime(), dto.getWaterInnerBed(), dto.getRain());
                }
                break;
            }
            case ntouToFengChia: {
                // if(!"64".equals(org_id)){
                // logger.warn("不是南投的資料喔!orgId={}",org_id);
                // break;
                // }
                // 組VO
                KafkaDTO dto = prepareDto.apply(kafka_message);
                String stationCodeName = ntouConfig.getFloodStnos().containsKey(st_no)
                        ? ntouConfig.getFloodStnos().get(st_no)
                        : ntouConfig.getWaterStnos().containsKey(st_no) ? ntouConfig.getWaterStnos().get(st_no)
                                : ntouConfig.getRainStnos().getOrDefault(st_no, null);

                if (stationCodeName == null) {
                    logger.warn("[FILTER] st_no={} 無法在任何站別找到對應 stationCodeName，略過處理", st_no);
                    break;
                }
                // 時間格式轉換
                String timeStr = dto.getDatatime();
                LocalDateTime localDateTime = LocalDateTime.parse(timeStr, formatter);
                ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("Asia/Taipei"));
                Instant stationTime = zonedDateTime.toInstant();

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
                    logger.warn("[FILTER] 未分類站點 st_no={}，略過處理", st_no);
                    break;
                }
                int resCode = 200;
                // int resCode = ntouToFengChiaService.postData(vo);
                logger.info(JsonConverter.convert(vo));
                break;
            }
            case tpeSewer: {
                if (tpeSewerService.hasStnosByKafkaString(kafka_message)) {
                    KafkaDTO dto = prepareDto.apply(kafka_message);
                    int resCode = tpeSewerService.postData(st_no,
                            Collections.singletonList(new TpeSewerPublisherPostVO().fromKafkaDTO(dto)));
                    logger.info("Tpesewer---topics={}, resCode={}, st_no={}, datatime={}, water_inner={}",
                            topices, resCode, st_no, dto.getDatatime(), dto.getWaterInner());
                }
                break;
            }
            case kaohsiungWrb: {
                if (kaohsiungWrbService.hasStnosByKafkaStringSewer(kafka_message)) {
                    KafkaDTO dto = prepareDto.apply(kafka_message);
                    int resCode = kaohsiungWrbService.postDataSewer(
                            Collections.singletonList(new KaohsiungWrbPublisherSewerPostVO().fromKafkaDTO(dto)));
                    logger.info("KaohsiungWrb.sewer---topics={}, resCode={}, st_no={}, datatime={}, water_inner={}",
                            topices, resCode, st_no, dto.getDatatime(), dto.getWaterInner());
                }
                if (kaohsiungWrbService.hasStnosByKafkaStringFlood(kafka_message)) {
                    KafkaDTO dto = prepareDto.apply(kafka_message);
                    int resCode = kaohsiungWrbService.postDataFlood(
                            Collections.singletonList(new KaohsiungWrbPublisherFloodPostVO().fromKafkaDTO(dto)));
                    logger.info("KaohsiungWrb.flood---topics={}, resCode={}, st_no={}, datatime={}, water_inner={}",
                            topices, resCode, st_no, dto.getDatatime(), dto.getWaterInner());
                }
                break;
            }
            case changhuaSewer: {
                if (floodStNos.indexOf(st_no) >= 0 || waterStNos.indexOf(st_no) >= 0) {
                    KafkaDTO dto = prepareDto.apply(kafka_message);
                    int resCode = changhuaService
                            .postData(Collections.singletonList(Util.toVo(dto, new ChsewerPostVO())));
                    logger.info("changhuaSewer---topics={}, resCode={}, st_no={}, datatime={}, water_inner={}",
                            topices, resCode, st_no, dto.getDatatime(), dto.getWaterInner());
                }

                break;
            }
            case changhuaFloodWater: {
                if (!"109".equals(org_id)) {
                    logger.error("這不是彰化縣的資料!orgId={}", org_id);
                    break;
                }
                // yaml過濾資料
                if (filterService.isFloodStation(st_no) || filterService.isWaterStation(st_no)) {
                    KafkaDTO dto = prepareDto.apply(kafka_message);
                    ChuploadPostVO vo = new ChuploadPostVO();
                    vo = vo.toChuploadPostVO(dto);

                    // int resCode = changhuaWatercenterService
                    // .uploadData(Collections.singletonList(vo.toChuploadPostVO(dto)));
                    int resCode = 200;
                    logger.info(JsonConverter.convert(vo));
                }
                break;
            }
            case ntpc: {
                if (!"110".equals(org_id)) {
                    break;
                }

                if ("raindata".equals(topices)) {
                    KafkaRainDTO dto = prepareRainDto.apply(kafka_message);
                    int resCode = newTaipeiService
                            .postRainData(Collections.singletonList(Util.toVo(dto, new RainDataDTO())));
                    logger.info("Ntpc---topics={}, resCode={}, st_no={}, datatime={}, rain={}",
                            topices, resCode, st_no, dto.getDatatime(), dto.getRain());
                    break;
                }

                KafkaDTO dto = prepareDto.apply(kafka_message);
                int resCode = newTaipeiService
                        .postData(Collections.singletonList(Util.toVo(dto, new FloodValueAllDTO())));
                logger.info("Ntpc---topics={}, resCode={}, st_no={}, datatime={}, water_inner={}",
                        topices, resCode, st_no, dto.getDatatime(), dto.getWaterInner());
                break;
            }
            case ntpcSewer: {
                if (!"110".equals(org_id)) {
                    break;
                }

                if ("raindata".equals(topices)) {
                    KafkaRainDTO dto = prepareRainDto.apply(kafka_message);
                    int resCode = newTaipeiSewerService
                            .postRainData(Collections.singletonList(Util.toVo(dto, new RainDataDTO())));
                    logger.info("Ntpc---topics={}, resCode={}, st_no={}, datatime={}, rain={}",
                            topices, resCode, st_no, dto.getDatatime(), dto.getRain());
                    break;
                }

                KafkaDTO dto = prepareDto.apply(kafka_message);
                int resCode = newTaipeiSewerService
                        .postData(Collections.singletonList(Util.toVo(dto, new FloodValueAllDTO())));
                logger.info("NtpcSewer---topics={}, resCode={}, st_no={}, datatime={}, water_inner={}",
                        topices, resCode, st_no, dto.getDatatime(), dto.getWaterInner());
                break;
            }
            default: {
                logger.warn("upload type={} not impl", publisherEnum.name());
                break;
            }
        }
    }

}
