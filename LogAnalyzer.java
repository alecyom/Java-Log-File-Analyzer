import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;

    public LogAnalyzer() {
        records = new ArrayList<LogEntry>();
     }

    public void readFile(String filename) {
        FileResource f = new FileResource();
        for (String s : f.lines()){
            LogEntry entry = WebLogParser.parseEntry(s);
            records.add(entry);
        }
     }
     
    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }

    public int countUniqueIPs(){
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry le : records){
            String ipAddr = le.getIpAddress();
            if (!uniqueIPs.contains(ipAddr)){
                uniqueIPs.add(ipAddr);
            }
        }
        return uniqueIPs.size();
    }

    public void printAllHigherThanNum(int num){
        for (LogEntry le : records){
            int statusCode = le.getStatuseCode();
            if (statusCode > num){
                System.out.println(le);
            }
        }
    }

    public ArrayList uniqueIPVisitsOnDay(String someday){
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        ArrayList<String> uniqueIPsDates = new ArrayList<String>();
        for (LogEntry le : records){
            Date d = le.getAccessTime();
            String str = d.toString();
            String subStr = str.substring(4,10);
            String ipAddr = le.getIpAddress();
            if(subStr.equals(someday) && !uniqueIPs.contains(ipAddr)){
                uniqueIPs.add(ipAddr);
                uniqueIPsDates.add(subStr);
            }
        }
        return uniqueIPs;
    }

    public int countUniqueIPsInRange(int low, int high){
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        ArrayList<Integer> uniqueIPsStatus = new ArrayList<Integer>();
        for (LogEntry le : records){
           int status = le.getStatuseCode();
           String ipAddr = le.getIpAddress();
           if (status >= low && status <= high && !uniqueIPs.contains(ipAddr)){
               uniqueIPs.add(ipAddr);
               uniqueIPsStatus.add(status);
            }
        }
        return uniqueIPsStatus.size();
    }
    public HashMap countVisitsPerIP(){
        HashMap<String,Integer> counts = new HashMap<String,Integer>();
        for(LogEntry le: records){
            String ip = le.getIpAddress();
            if(!counts.containsKey(ip)){
                counts.put(ip,1);
            }
            else {
                counts.put(ip,counts.get(ip)+1);
            }
        }
        return counts;
    }

    public int mostNumberVisitsByIP(HashMap<String, Integer> counts){
        index = 0;
        for (int num : counts.values()){
            if (index < num){
                index = num;
            }
        }
        return index;
    }

    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> counts){
        ArrayList<String> maxIPs = new ArrayList<String>();
        for (String s : counts.keySet()){
            if (counts.get(s) == index){
                maxIPs.add(s);
            }
        }
        return maxIPs;
    }

    public HashMap<String, ArrayList<String>> iPsForDays(){
        HashMap<String,ArrayList<String>> daysIps = new HashMap<String,ArrayList<String>>();

        for(LogEntry le: records){
            ArrayList<String> date = new ArrayList<String>();
            String ip = le.getIpAddress();
            Date d = le.getAccessTime();
            String str = d.toString();
            String dateStr = str.substring(4,10);
            if(!daysIps.containsKey(ip)){
                date.add(dateStr);
                daysIps.put(ip,date);

            }
            else{
                date = daysIps.get(ip);
                if (!date.contains(dateStr)){
                    date.add(dateStr);
                }
            }
        }
        return daysIps;
    }

    public HashMap<String, ArrayList<String>> dayCountHash(){
         HashMap<String,ArrayList<String>> datesIpMap = new HashMap<String,ArrayList<String>>();
        for(LogEntry le: records){
            ArrayList<String>ipArray = new ArrayList<String>();
            String ip = le.getIpAddress();
            Date d = le.getAccessTime();
            String str = d.toString();
            String dateStr = str.substring(4,10);
            if(!datesIpMap.containsKey(dateStr)){
                ipArray.add(ip);
                datesIpMap.put(dateStr,ipArray);
            }
            else{
                ipArray = datesIpMap.get(dateStr);
                if (!ipArray.contains(ip)){
                    ipArray.add(ip);
                }
            }
        }
        return datesIpMap;
    }
}
