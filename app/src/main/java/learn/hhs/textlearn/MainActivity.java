package learn.hhs.textlearn;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import learn.hhs.textlearn.decrypt.DecryptText;
import learn.hhs.textlearn.encrypt.EncryptText;

public class MainActivity extends AppCompatActivity
{
    Button encryptButton, decryptButton,clipBoardButton;
    EditText plainText,passkey,cipherText;
    TextView decryptedText,messageText;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        encryptButton = (Button)findViewById(R.id.encryptButton);
        decryptButton = (Button)findViewById(R.id.decryptButton);
        clipBoardButton = (Button)findViewById(R.id.clipboardButton);
        plainText = (EditText)findViewById(R.id.plainText);
        passkey =(EditText)findViewById(R.id.passkey);
        cipherText = (EditText)findViewById(R.id.cipherText);
        decryptedText = (TextView)findViewById(R.id.decryptedText);
        messageText = (TextView)findViewById(R.id.messageLabel);

        encryptButton.setOnClickListener(new View.OnClickListener()
        {
            @SuppressLint("SetTextI18n")
            public void onClick(View view)
            {
                try
                {
                    messageText.setText("");
                    EncryptText encryptText = new EncryptText();
                    String plain=plainText.getText().toString();
                    String pass = passkey.getText().toString();
                    String encryptionStandard = "AES";

                    if(pass.length()>0 & plain.length()>0)
                    {
                        String encrypted = encryptText.encryptData(plain, pass, encryptionStandard);
                        cipherText.setText(encrypted);
                        decryptedText.setText("Encryption in progress");
                        plainText.setText("");
                    }
                    else {
                        if(plain.length()<1)
                            messageText.setText("No input !");
                        else
                            messageText.setText("Key is null!");
                    }
                }
                catch (Exception e)
                {
                    messageText.setText("Error during encryption!");
                }
            }
        });

        decryptButton.setOnClickListener(new View.OnClickListener()
        {
            @SuppressLint({"SetTextI18n", "WrongViewCast"})
            public void onClick(View view)
            {
                try
                {
                    messageText.setText("");
                    DecryptText decryptText = new DecryptText();
                    String cipher=cipherText.getText().toString();
                    String pass = passkey.getText().toString();
                    String encryptionStandard = "AES";
                    String decrypted = decryptText.decryptData(cipher, pass, encryptionStandard);
                    decryptedText.setText(decrypted);
                }
                catch (Exception e)
                {
                    messageText.setText("Incorrect password");
                    decryptedText.setText(" ");
                }
            }
        });

        clipBoardButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {
                if(cipherText.getText().toString().length() > 0)
                {
                    ClipboardManager myClipboard = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
                    ClipData myClip;
                    myClip = ClipData.newPlainText("text",cipherText.getText().toString());
                    myClipboard.setPrimaryClip(myClip);
                }
                else
                    messageText.setText("Nothing to copy !");
            }
        }
        );
    }
}
