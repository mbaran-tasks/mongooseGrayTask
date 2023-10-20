package TestCasesTask;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class TestStringCollection {
    ExcelUtil testData = new ExcelUtil("src/test/java/TestCasesTask/StringCollection.xlsx","Sheet1");
    int currentRow = 1;
    @DataProvider
    public Object[][] testData(){
        String [][] dataArray = testData.getDataArrayWithoutFirstRow();
        return dataArray;
    }

    @Test(dataProvider = "testData")
    public void testMyStringCollection(String TestCases, String TestScope, String inputString, String outputString){
        String outputData= "";
        boolean flag = false;
        StringBuilder processedString = new StringBuilder();
        char lastChar = '\0';

            for (char currentChar : inputString.toCharArray()) {
                if (currentChar == '$') {
                    processedString.append('£');
                } else if (currentChar == '_' || currentChar == '4') {
                    continue;
                } else if (currentChar != lastChar) {
                    processedString.append(currentChar);
                    lastChar = currentChar;
                }
            }
            if(processedString!=null && !processedString.toString().equals("") && !processedString.toString().equals(".0")) {
                flag = true;
                if(processedString.length()<=15){
                    outputData = processedString.toString();
                    testData.setCellData(outputData, "outputString", currentRow);
                    Assert.assertTrue(flag);
                }else {
                    outputData = processedString.substring(0, 15);
                    testData.setCellData(outputData, "outputString", currentRow);
                    Assert.assertTrue(flag);
                }
            }else{
                testData.setCellData("", "outputString", currentRow);
                currentRow++;
            }
            Assert.assertNotNull(outputData);
            Assert.assertNotEquals(outputData,"");

            System.out.println("----------"+TestCases+"----------");
            System.out.println(TestScope);
            System.out.println("Input string: "+inputString);
            System.out.println("Output string: "+ outputData);
            currentRow++;
        }

}
