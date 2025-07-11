/*
package daemon.go.kr.api.collection;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;


//1인당 GRDP
public class Person_GRDP {

    public static void main(String[] args) {
    	// 기본값
    	String year = "2022"; 

        // 사용자로부터 연도 입력받고 예외 처리
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("출력할 연도를 입력하세요 (예: 2022) :");
            System.out.print("기본년도는 2022년입니다 ");
            String input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                if (input.matches("\\d{4}")) {
                    year = input;
                } else {
                    System.out.println("⚠️ 올바른 형식의 연도가 아닙니다. 기본값(2022년)으로 진행합니다.");
                }
            } else {
                System.out.println("⚠️ 입력이 없어 기본값(2022년)으로 진행합니다.");
            }
        } catch (Exception e) {
            System.out.println("⚠️ 입력 중 오류가 발생했습니다. 기본값(2022년)으로 진행합니다.");
        }

        try {
            // API URL 설정
            String apiUrl = "https://kosis.kr/openapi/Param/statisticsParameterData.do?method=getList" +
                    "&apiKey=NzhiYWE5NDBiOWExZjdkNWI4ODlhZDc4ZTYxMzZkMzE=" +
                    "&itmId=T1+T2+T3+T4+" +
                    "&objL1=ALL" +
                    "&format=json" +
                    "&jsonVD=Y" +
                    "&prdSe=Y" +
                    "&newEstPrdCnt=100" +
                    "&orgId=101" +
                    "&tblId=DT_1C86";

            String jsonData = fetchData(apiUrl);
            JSONArray dataArray = new JSONArray(jsonData);

            // 시도별 데이터를 맵에 정리
            Map<String, Map<String, String>> regionDataMap = new LinkedHashMap<>();

            // 시도 정렬 순서
            List<String> regionOrder = Arrays.asList(
                    "전국", "서울특별시", "부산광역시", "대구광역시", "인천광역시", "광주광역시",
                    "대전광역시", "울산광역시", "세종특별자치시", "경기도", "강원도",
                    "충청북도", "충청남도", "전라북도", "전라남도", "경상북도", "경상남도", "제주특별자치도"
            );

            for (int i = 0; i < dataArray.length(); i++) {
                JSONObject obj = dataArray.getJSONObject(i);
                String dataYear = obj.optString("PRD_DE");
                String region = obj.optString("C1_NM");
                String item = obj.optString("ITM_ID");
                String value = obj.optString("DT");

                if (!dataYear.equals(year)) continue;

                // 시도별로 초기화
                regionDataMap.putIfAbsent(region, new HashMap<>());
                Map<String, String> itemMap = regionDataMap.get(region);
                itemMap.put(item, value);
            }

            // 엑셀 생성
            XSSFWorkbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("KOSIS Data");

            // 헤더
            Row header = sheet.createRow(0);
            String[] columns = {"시점", "시도별", "1인당 지역내총생산", "1인당 지역총소득", "1인당 개인소득","1인당 민간소비"};
            for (int i = 0; i < columns.length; i++) {
                header.createCell(i).setCellValue(columns[i]);
            }

            // 데이터 작성
            int rowNum = 1;
            for (String region : regionOrder) {
                if (regionDataMap.containsKey(region)) {
                    Map<String, String> data = regionDataMap.get(region);
                    
                    // SQL Insert 문 생성
                    String sql = String.format(
                        "INSERT INTO TOD_PER_CAPI_GRDP (YEAR, CITI_PROV, GROS_REGO_PROC_PER_CAPI, GROS_REGO_INCM_PER_CAPI, PERS_INCM_PER_CAPI, PRIV_CONS_PER_PERS, REG_ID, REG_TM, MODF_ID, MODF_TM) " +
                        "VALUES ('%s', '%s', %s, %s, %s, %s, 'BATCH', SYSDATE, 'BATCH', SYSDATE);",
                        year, region,
                        data.getOrDefault("T1", "0"),
                        data.getOrDefault("T2", "0"),
                        data.getOrDefault("T3", "0"),
                        data.getOrDefault("T4", "0")
                    );

                    
                    Row row = sheet.createRow(rowNum++);
                    row.createCell(0).setCellValue(year);
                    row.createCell(1).setCellValue(region);
                    row.createCell(2).setCellValue(data.getOrDefault("T1", ""));
                    row.createCell(3).setCellValue(data.getOrDefault("T2", ""));
                    row.createCell(4).setCellValue(data.getOrDefault("T3", ""));
                    row.createCell(5).setCellValue(data.getOrDefault("T4", ""));
                    row.createCell(6).setCellValue(sql);
         
                }
            }

            // 저장
            String filePath = System.getProperty("user.home") + "/Desktop/1인당_GRDP_KOSIS_Data_" + year + ".xlsx";
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }
            workbook.close();

            System.out.println("✅ 엑셀 파일이 바탕화면에 저장되었습니다: " + filePath);

        } catch (Exception e) {
            System.out.println("❌ 처리 중 오류 발생:");
            e.printStackTrace();
        }
    }

    private static String fetchData(String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }
}
*/