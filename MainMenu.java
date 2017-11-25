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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainMenu extends AppCompatActivity {


    ListView listView1;
    ArrayList<String> texteliste;

    Button btn;
    // Zahl willkürlich gewählt
    int REQ_CODE_EXTERNAL_STORAGE_PERMISSION = 45;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Vibrator v = (Vibrator) getSystemService(VIBRATOR_SERVICE) ;
        v.vibrate(1000);

        texteliste = new ArrayList<String>();
        texteliste.add("String1");
        texteliste.add("String2");
        texteliste.add("String3");
        texteliste.add("String4");



        //TESTKOMMMENTARE
        //TESTKOMMMENTARE
        //TESTKOMMMENTARE
        //TESTKOMMMENTARE
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

        listView1 = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainMenu.this, android.R.layout.simple_list_item_1, texteliste);
        listView1.setAdapter(arrayAdapter);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "Auf ListItem geklickt!",Toast.LENGTH_SHORT).show();
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
