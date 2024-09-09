import abstra.hooks as ah
from abstra.connectors import get_gmail_credentials
from googleapiclient.discovery import build
from email.message import EmailMessage
import base64

body, query, headers = ah.get_request()

credentials = get_gmail_credentials()
service = build('gmail', 'v1', credentials=credentials)

html_card = """
<td style="text-align:left;padding:30px;background:#f3f4f6">
    <div style="text-align:center;padding:30px;background:#ffffff;width:80%;max-width:600px;margin:0 auto">
        
        <table role="presentation" style="margin:0 auto;width:80%">
            
            <tbody><tr>
                <td>
                    <table role="presentation" cellpadding="0" cellspacing="0" style="width:100%">
                        <tbody><tr>
                            <td>
                                <p style="font-style:normal;font-weight:700;font-size:30px;line-height:36px;color:#181818;margin-bottom:20px;margin-top:30px">
                                    Literario Login
                                </p>
                                <p style="font-style:normal;font-weight:400;font-size:16px;line-height:24px;color:#252525;margin:0">
                                    Você solicitou autenticação em <a href="https://literar.io" target="_blank">https://literar.io</a>
                                </p>
                                <p style="font-style:normal;font-weight:400;font-size:16px;line-height:24px;color:#252525;margin:0">
                                    Insira o códico de confirmação absixo na tela de login
                                </p>
                                <p style="font-style:normal;font-weight:700;font-size:30px;line-height:36px;color:#181818;margin-bottom:20px;margin-top:30px">
                                    {verification_code}
                                </p>
                            </td>
                        </tr>
                        <tr style="height:50px">
                            <td>
                                <div style="width:100%;height:1px;background-color:#eeeeee"></div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div>
                                    <img style="height:21px;display:inline;vertical-align:middle" src="https://ci3.googleusercontent.com/meips/ADKq_NZvCELIieLXLAOnjEjKoGvIU-T5baldtE2Xt5Iu9Q4avhF7PJsKagwKQOVodD0j077KCMWTQ3HX_MnBLHeSZ4PIYktaVE6Yxcrp4YI5wSJ20SMWO9FcDLsT6CtOqQ=s0-d-e1-ft#https://abstra-cloud-assets.s3.amazonaws.com/bi_hourglass-split+(1).png" class="CToWUd" data-bit="iit">
                                    <p style="font-style:normal;font-weight:700;font-size:16px;line-height:21px;color:#707070;margin:0;display:inline;vertical-align:middle">
                                        Esse código de verifição expira em 15 minutos
                                    </p>
                                </div>
                            </td>
                        </tr>
                    </tbody></table>
                </td>
            </tr><tr style="height:70px">
                <td style="font-size:12px;color:#252525;text-align:bottom">
                    <div style="float:bottom">Se você não solicitou altenticação Literario, ignore essa mensagem.</div>
                </td>
            </tr>


</tbody></table>

</div>

<div>
<div style="text-align:center">
<table role="presentation" cellpadding="0" cellspacing="0">
<tbody><tr>
    <td style="font-style:normal;font-weight:700;font-size:12px;line-height:20px;color:#414a58;text-align:center">
    </td>
</tr>
</tbody></table>
</div>
</div>

</td>


"""

message = EmailMessage()
message['to'] = body['to']
message['from'] = 'authenticate.literario@gmail.com'
message['subject'] = 'Login Literario'

message.set_content(html_card.format(verification_code=body['code']), subtype='html')

raw_message = base64.urlsafe_b64encode(message.as_bytes()).decode()

message = service.users().messages().send(userId='me', body={'raw': raw_message}).execute()
print(f'Message Id: {message["id"]}')
