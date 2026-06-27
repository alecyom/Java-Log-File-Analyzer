# Web Log Analyzer
 
A small Java program for parsing and analyzing Apache-style web server log files.
The program converts log lines to into structured `LogEntry` objects, and provides 
methods for analyzing traffic. This includes unique visitor counts, status code
filtering, per-IP visit counts, and per-day visitor breakdowns.

## Class Descriptions

### LogEntry
A data class that holds parsed data from a web log line. This includes the IP address, 
the request time, the HTTPS request string, HTTPS response code, and response size. 
Provides getters for each data field.

### WebLogParser
A static helper class with one public method. Parses a log line to make a log entry.

### LogAnalyzer
The main analyzer class that provides methods for analyzing log entries. Here are the
following methods
| Method | Description |
|---|---|
| `readFile(String filename)` | Reads a log file, parses each line with `WebLogParser`, and stores the parsed entries. |
| `printAll()` | Prints every stored log entry. |
| `countUniqueIPs()` | Returns the number of unique IP addresses. |
| `printAllHigherThanNum(int num)` | Prints every entry with status code greater than `num`. |
| `uniqueIPVisitsOnDay(String someday)` | Returns the list of unique IPs that visited on 'someday'. |
| `countUniqueIPsInRange(int low, int high)` | Counts unique IPs with a status code within `[low, high]`. |
| `countVisitsPerIP()` | Returns a `HashMap<String, Integer>` with the total number of visits for each IP. |
| `mostNumberVisitsByIP(HashMap<String, Integer> counts)` | Returns entry with the highest visit count in `HashMap<String, Integer>`. |
| `iPsMostVisits(HashMap<String, Integer> counts)` | Returns a list of IPs with the highest visit count. |
| `iPsForDays()` | Returns a `HashMap<String, ArrayList<String>>` with the different days an IP has visited. |
| `dayCountHash()` | Returns a `HashMap<String, ArrayList<String>>` with a list of IPs that visited on a certain fay. |

### Tester
The test class that provides test methods to output results for the analysis methods in
'LogAnalyzer'.

## Dependencies
 
- **Java** — Any recent JDK
- **Duke University's `edu.duke` library** — specifically `FileResource`, used by
`LogAnalyzer.readFile()` to read log files. This is the helper library from Duke's
Coursera "Java Programming: Arrays, Lists, and Structured Data" course. You'll need
`duke_algs4.jar` on your classpath to compile and run this project.

