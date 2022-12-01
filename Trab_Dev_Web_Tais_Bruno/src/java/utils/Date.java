package utils;

public class Date {
    public static String format(String date, String type){
        
        String result, resultWithHour = "";
        String[] splitDate, splitHour; 
        String day, month, year, hour, data;
        
        switch (type) {
            case "database":
                if (date != null) {
                    splitDate = date.split("/");

                    day = splitDate[0];
                    month = splitDate[1];
                    year = splitDate[2];
                    
                    resultWithHour = String.join("-", year, month, day);

                }
                                
                break;
            case "brazil":   
                if (date != null) {
                    splitDate = date.split("-");

                    year = splitDate[0];
                    month = splitDate[1];
                    day = splitDate[2];
                    
                    splitHour = day.split(" ");
                    data = splitHour[0];
                    hour = splitHour[1];
                            
                    result = String.join("/", data, month, year); // [dd/mm/yyyy]
                    
                    resultWithHour = String.join(" ", result, hour);
                    
                }
                break;
                
            case "backToDatabase":
                if (date != null) {
                    splitDate = date.split("/");

                    day = splitDate[0];
                    month = splitDate[1];
                    year = splitDate[2];
                    
                    splitHour = year.split(" ");
                    data = splitHour[0];
                    hour = splitHour[1];
                    
                    result = String.join("-", data, month, day);
                    resultWithHour = String.join(" ", result, hour);
                }
                                
                break;
                
            case "brazilonlyDate":   
                if (date != null) {
                    splitDate = date.split("-");

                    year = splitDate[0];
                    month = splitDate[1];
                    day = splitDate[2];
                    
                    splitHour = day.split(" ");
                    data = splitHour[0];
                    hour = splitHour[1];
                            
                    resultWithHour = String.join("/", data, month, year); // [dd/mm/yyyy]
                   
                }
                break;
        }
        
        return resultWithHour;
    }    
}
