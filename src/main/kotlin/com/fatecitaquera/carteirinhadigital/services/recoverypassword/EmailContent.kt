package com.fatecitaquera.carteirinhadigital.services.recoverypassword

fun emailContent(token: String): String {
    return """
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
</head>
<body style="margin: 0; padding: 0; background-color: #f4f6f8; font-family: Arial, Helvetica, sans-serif;">
    <table width="100%" cellpadding="0" cellspacing="0">
        <tr>
            <td align="center">
                <table width="600" cellpadding="0" cellspacing="0" 
                       style="background-color: #ffffff; margin-top: 40px; border-radius: 8px; overflow: hidden; box-shadow: 0 4px 12px rgba(0,0,0,0.1);">
                    
                    <!-- Header -->
                    <tr>
                        <td style="background-color: #8b0000; padding: 20px; text-align: center; color: #ffffff;">
                            <h1 style="margin: 0; font-size: 24px;">Fatec Itaquera</h1>
                        </td>
                    </tr>

                    <!-- Content -->
                    <tr>
                        <td style="padding: 30px; color: #333333;">
                            <p style="font-size: 16px;">Olá,</p>

                            <p style="font-size: 15px; line-height: 1.6;">
                                Você solicitou a <b>recuperação de senha</b> para sua conta.
                            </p>

                            <p style="font-size: 15px; margin-top: 20px;">
                                Utilize o código abaixo para continuar o processo:
                            </p>

                            <div style="margin: 30px 0; text-align: center;">
                                <span style="
                                    display: inline-block;
                                    background-color: #f0f0f0;
                                    padding: 15px 25px;
                                    font-size: 22px;
                                    letter-spacing: 3px;
                                    font-weight: bold;
                                    border-radius: 6px;
                                    color: #000000;
                                ">
                                    $token
                                </span>
                            </div>

                            <p style="font-size: 14px; color: #666666; line-height: 1.6;">
                                Se você não solicitou esta recuperação, basta ignorar este e-mail.
                            </p>

                            <p style="margin-top: 30px; font-size: 15px;">
                                Atenciosamente,<br>
                                <b>Equipe Fatec Itaquera</b>
                            </p>
                        </td>
                    </tr>

                    <!-- Footer -->
                    <tr>
                        <td style="background-color: #f4f6f8; padding: 15px; text-align: center; font-size: 12px; color: #999999;">
                            © ${java.time.Year.now()} Fatec Itaquera — Todos os direitos reservados
                        </td>
                    </tr>

                </table>
            </td>
        </tr>
    </table>
</body>
</html>
""".trimIndent()
}