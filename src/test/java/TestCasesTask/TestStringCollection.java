package TestCasesTask;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class TestStringCollection {
    ExcelUtil testData = new ExcelUtil("src/test/java/TestCasesTask/StringCollection.xlsx","Sheet1");

    @DataProvider
    public Object[][] testData(){
        String [][] dataArray = testData.getDataArrayWithoutFirstRow();
        return dataArray;
    }

    @Test(dataProvider = "testData")
    public void testMyStringCollection(String TestCases, String TestScope, String inputString){
        String outputString= "";
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
            if(processedString.length()<=15){
                outputString = processedString.toString();
            }else {
                outputString = processedString.substring(0, 15);
            }
            Assert.assertNotNull(outputString);
            Assert.assertNotEquals(outputString,"");
            System.out.println("----------"+TestCases+"----------");
            System.out.println(TestScope);
            System.out.println("Input string: "+inputString);
            System.out.println("Output string: "+ outputString);



        }

}
