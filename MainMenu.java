package lerndatenbankversion10.de;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class MainMenu extends AppCompatActivity {

    Button btn;
    // Zahl willkürlich gewählt
    int REQ_CODE_EXTERNAL_STORAGE_PERMISSION = 45;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);



        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //Checkt ob die Permission gewährt wurde, auf den Speicher zuzugreifen!
                //Fkt. gibt einen Integer zurürck
                if(ActivityCompat.checkSelfPermission(MainMenu.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                    createFolder();
                }else{
                    ActivityCompat.requestPermissions(MainMenu.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQ_CODE_EXTERNAL_STORAGE_PERMISSION);
                }
            }
        });


    }



    private void createFolder(){
        File ordner = new File(Environment.getExternalStorageDirectory(), "TestOrdnerHeHe");
        ordner.mkdirs();
        Toast.makeText(getApplicationContext(), "Ordner erstellt!", Toast.LENGTH_SHORT).show();
    }

    // Bei dieser Methode kommen die Spez. REQ_CODES an
    // Wird der Request vom Nutzer zugestimmt kommt REQ_CODE_EXTERNAL_STORAGE_PERMISSION hier an und wird in das Array gepackt
    // grantResults[] enthält dann die REQ_CODEs von allen Abfragen (Kamera,BIlder etc......)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == REQ_CODE_EXTERNAL_STORAGE_PERMISSION && grantResults.length > 0 &&grantResults[0] == PackageManager.PERMISSION_GRANTED){
            createFolder();

        }



    }
}
