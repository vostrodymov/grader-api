import hashlib
import json
from xml.dom import minidom

conversionDict = {'ignore': 'info', 'info': 'minor', 'warning': 'major', 'error': 'critical'}

if __name__ == "__main__":
    import sys

    checkstyle_file = sys.argv[1]
    output_file = sys.argv[2]

    # parse an xml file by name
    file = minidom.parse(checkstyle_file)

    # use getElementsByTagName() to get tag
    files = file.getElementsByTagName('file')
    errorArray = []

    for file in files:
        errors = file.getElementsByTagName('error')

        for error in errors:
            print('\n')
            finger = file.attributes['name'].value + error.attributes['line'].value + error.attributes['message'].value
            fingerprint = hashlib.sha256(finger.encode('utf-8')).hexdigest()
            print("description: ", error.attributes['message'].value)
            print("fingerprint: ", fingerprint)
            print("severity: ", conversionDict.get(error.attributes['severity'].value))
            print("location: ", file.attributes['name'].value)
            print("line: ", error.attributes['line'].value)
            lines = {"begin": int(error.attributes['line'].value)}
            location = {"path": file.attributes['name'].value, "lines": lines, }
            data = {
                "description": error.attributes['message'].value,
                "fingerprint": fingerprint,
                "severity": conversionDict.get(error.attributes['severity'].value),
                "location": location,
            }
            errorArray.append(data)
            print('\n')
            print(data)

    print('\nDone')

    json_object = json.dumps(errorArray, indent=2)

    with open(output_file, "w") as outfile:
        outfile.write(json_object)
