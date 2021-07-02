CSV Formatter

Command-line application to input CSV file, perform required conversions based on configuration and input parameters and generate output CSV file in required format.

Salient points to be noted:
1. Sample and template to be followed are available in "Ajira\src\main\res\csv\" folder for reference.
2. INPUT_FILE_PATH is filesystem path of the input.csv.
For Example - Students.csv, Friends.csv, Employees.csv, People.csv
3. CONFIG_FILE_PATH
Config feeds in the input file path and generates to the desired output file path.

Legend	Details
1	Change the data in the dob column from yyyy-mm-dd format to MMM dd, YYYY
2	Remove friends who has the age greater than 50
3	merge first_name and last_name into a single column name with the format {FIRST_NAME}, {LAST NAME}
4	create a new column eligible_for_voting with the value as T if age > 18 else F

For Example - Config.csv
4. OUTPUT_FILE_PATH
Required CSV is generated in the file path mentioned.
For Example - Output.csv

Steps to run:

Source:
 
1. Run Config.java with three input parameters as given below:

java Config {INPUT_FILE_PATH} {CONFIGURATION_FILE_PATH} {OUTPUT_FILE_PATH}

Test:

1. Unit Test Files are present in C:\Users\289320\Downloads\Medical Claim\Ajira\src\test folder. 
Please remember to configure the source of the CSV files for below file path in ConfigTest.java

INVALID_DIR_PATH
INVALID_FILE_PATH
STUDENTS_FILE_PATH 
FRIENDS_FILE_PATH
EMPLOYEES_FILE_PATH
CONFIG_FILE_PATH
INVALID_CONFIG_FILE_PATH
OUTPUT_FILE_PATH

Happy coding :)


