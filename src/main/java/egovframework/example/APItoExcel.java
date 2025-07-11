/*
package egovframework.example;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

//행정구역읍면별 5세
public class APItoExcel {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("조회할 년도를 입력하세요 (예: 2023): ");
        String year = scanner.nextLine().trim();
        scanner.close();

        String apiUrl = "https://kosis.kr/openapi/Param/statisticsParameterData.do?method=getList"
                + "&apiKey=NzhiYWE5NDBiOWExZjdkNWI4ODlhZDc4ZTYxMzZkMzE="
                + "&itmId=T2+T3+T4+"
                + "&objL1=00+11+11110+11140+11170+11200+11215+11230+11260+11290+11305+11320+11350+11380+11410+11440+11470+11500+11530+11545+11560+11590+11620+11650+11680+11710+11740+26+26110+26140+26170+26200+26230+26260+26290+26320+26350+26380+26410+26440+26470+26500+26530+26710+27+27110+27140+27170+27200+27230+27260+27290+27710+27720+28+28110+28140+28170+28177+28185+28200+28237+28245+28260+28710+28720+29+29110+29140+29155+29170+29200+30+30110+30140+30170+30200+30230+31+31110+31140+31170+31200+31710+36+36110+41+41105+41110+41130+41150+41170+41190+41210+41220+41250+41270+41280+41290+41310+41360+41370+41390+41410+41430+41450+41460+41480+41500+41550+41570+41590+41610+41630+41650+41670+41730+41800+41820+41830+51+51105+51110+51130+51150+51170+51190+51210+51230+51720+51730+51750+51760+51770+51780+51790+51800+51810+51820+51830+43+43110+43130+43150+43710+43720+43730+43740+43745+43750+43760+43770+43800+44+44130+44150+44180+44200+44210+44230+44250+44270+44710+44730+44760+44770+44790+44800+44810+44825+44830+52+52110+52130+52140+52180+52190+52210+52710+52720+52730+52740+52750+52770+52790+52800+46+46110+46130+46150+46170+46230+46710+46720+46730+46770+46780+46790+46800+46810+46820+46830+46840+46860+46870+46880+46890+46900+46910+47+47110+47130+47150+47170+47190+47210+47230+47250+47280+47290+47720+47730+47750+47760+47770+47820+47830+47840+47850+47900+47920+47930+47940+48+48120+48170+48220+48240+48250+48270+48310+48330+48720+48730+48740+48820+48840+48850+48860+48870+48880+48890+50+50110+50130+"
                + "&objL2=ALL"
                + "&format=json"
                + "&jsonVD=Y"
                + "&prdSe=Y"
                + "&startPrdDe=" + year
                + "&endPrdDe=" + year
                + "&orgId=101"
                + "&tblId=DT_1B04005N";

        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(apiUrl).openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder jsonBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }
            reader.close();

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(jsonBuilder.toString());

            Map<String, String[]> dataMap = new LinkedHashMap<>();
            Set<String> regionSet = new LinkedHashSet<>();
            Set<String> ageGroupSet = new HashSet<>();

            for (JsonNode node : root) {
                String region = node.get("C1_NM").asText();     // 행정구역
                String ageGroup = node.get("C2_NM").asText();   // 5세별
                String item = node.get("ITM_NM").asText();      // 총인구수 / 남자 / 여자
                String value = node.get("DT").asText();         // 수치

                regionSet.add(region);
                ageGroupSet.add(ageGroup);

                String key = region + "|" + ageGroup;
                String[] arr = dataMap.getOrDefault(key, new String[]{"", "", ""});

                switch (item) {
                    case "총인구수": arr[0] = value; break;
                    case "남자인구수": arr[1] = value; break;
                    case "여자인구수": arr[2] = value; break;
                }

                dataMap.put(key, arr);
            }

            // 나이 그룹 순서 정의
            List<String> predefinedAgeOrder = Arrays.asList(
                "계","0 - 4세", "5 - 9세", "10 - 14세", "15 - 19세", "20 - 24세", "25 - 29세",
                "30 - 34세", "35 - 39세", "40 - 44세", "45 - 49세", "50 - 54세", "55 - 59세",
                "60 - 64세", "65 - 69세", "70 - 74세", "75 - 79세", "80 - 84세", "85 - 89세",
                "90 - 94세", "95 - 99세", "100세 이상"
            );

            // ageGroupSet → List 변환 후 명시적 순서로 정렬
            List<String> ageGroups = new ArrayList<>(ageGroupSet);
            ageGroups.sort(Comparator.comparingInt(age -> {
                int idx = predefinedAgeOrder.indexOf(age);
                return idx == -1 ? Integer.MAX_VALUE : idx;
            }));

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("인구통계_" + year);

            //년도 표시
            Row yearRow = sheet.createRow(0);
            yearRow.createCell(2).setCellValue(year);  // C 열

            // 제목 행
            Row header = sheet.createRow(1);
            header.createCell(0).setCellValue("행정구역 읍면동별");
            header.createCell(1).setCellValue("5세별");
            header.createCell(2).setCellValue("총인구수 (명)");
            header.createCell(3).setCellValue("남자인구수 (명)");
            header.createCell(4).setCellValue("여자인구수 (명)");
            header.createCell(5).setCellValue("No 순서");

            int rowNum = 2;
            for (String region : regionSet) {
                for (String ageGroup : ageGroups) {
                    String key = region + "|" + ageGroup;
                    String[] values = dataMap.getOrDefault(key, new String[]{"", "", ""});
                    
                    // SQL문 생성
                    String sql = String.format("INSERT INTO TOD_REGO_ECON_POPL_STRC (YEAR, ADMI_DIVI, BY_AGE_5, NUM_TOT_POPL, NUM_M_POPL, NUM_W_POPL, NO) VALUES ('%s', '전국', '%s', %s, %s, %s, %d);", 
                        year, ageGroup, values[0], values[1], values[2], rowNum-1);

                    Row row = sheet.createRow(rowNum++);
                    row.createCell(0).setCellValue(region);
                    row.createCell(1).setCellValue(ageGroup);
                    row.createCell(2).setCellValue(values[0]); // 총인구수
                    row.createCell(3).setCellValue(values[1]); // 남자
                    row.createCell(4).setCellValue(values[2]); // 여자  
                    row.createCell(5).setCellValue(rowNum-2); // 순서
                    row.createCell(6).setCellValue(sql); // SQL문
                }
              
            }

            String desktopPath = System.getProperty("user.home") + "\\Desktop";
            String fileName = desktopPath + "\\행정구역 읍면동별_" + year + ".xlsx";
            FileOutputStream fileOut = new FileOutputStream(fileName);
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();

            System.out.println("엑셀 생성 완료: " + fileName);

        } catch (Exception e) {
            System.out.println("에러 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
*/

