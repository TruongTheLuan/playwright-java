package data;

public class FormTestData {
    public static String formValidationData = """
           [
                             {
                               "Full Name": {
                                 "input": "",
                                 "expected": "Please input your full name!"
                               },
                               "Email": {
                                 "input": "",
                                 "expected": "Please input your email!"
                               },
                               "Phone Number": {
                                 "input": "",
                                 "expected": "Please input your phone number!"
                               },
                               "Date of Birth": {
                                 "input": "",
                                 "expected": "Please select your date of birth!You must be at least 18 years old!"
                               },
                               "Address": {
                                 "input": "",
                                 "expected": "Please input your address!"
                               }
                             },
                             {
                               "Full Name": {
                                 "input": "",
                                 "expected": "Please input your full name!"
                               },
                               "Email": {
                                 "input": "abc.com",
                                 "expected": "The input is not valid E-mail!"
                               },
                               "Phone Number": {
                                 "input": "987654321",
                                 "expected": "Phone number must be 10 digits!"
                               },
                               "Date of Birth": {
                                 "input": "2024-12-03",
                                 "expected": "You must be at least 18 years old!"
                               },
                               "Address": {
                                 "input": "",
                                 "expected": "Please input your address!"
                               }
                             },
                             {
                               "Full Name": {
                                 "input": "",
                                 "expected": "Please input your full name!"
                               },
                               "Email": {
                                 "input": "abc.com",
                                 "expected": "The input is not valid E-mail!"
                               },
                               "Phone Number": {
                                 "input": "09878987892",
                                 "expected": "Phone number must be 10 digits!"
                               },
                               "Date of Birth": {
                                 "input": "AAA",
                                 "expected": "Please select your date of birth!You must be at least 18 years old!"
                               },
                               "Address": {
                                 "input": "",
                                 "expected": "Please input your address!"
                               }
                             }
                           ]
     """;
    public static String formValidData = """
     [
        {
            "Full Name": "test",
            "Email": "test@gmail.com",
            "Phone Number": "0987675271",
            "Date of Birth": "1999-01-01",
            "Address": "HCM",
            "Occupation": "ADB",
            "Company": "ABC"
        }
     ]
     """;
}
