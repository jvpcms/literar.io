import base64

json = '{"username":"1234567890","perm":"adm","iat":1516239022,"exp":"terca"}'
encoded = base64.b64encode(json.encode('utf-8'))
print(encoded)