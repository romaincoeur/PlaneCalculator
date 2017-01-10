package fr.wildcodeschool.planecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private PlaneModel myPlane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myPlane = new PlaneModel(578.0, 900.0, 110.0, 0.72, 0.205, 0.564, 0.372, 0.410, 1.190, 1.900, 1.120);

        final EditText passengerName1 = (EditText)findViewById(R.id.nomPass1);
        final EditText passengerName2 = (EditText)findViewById(R.id.nomPass2);
        final EditText passengerName3 = (EditText)findViewById(R.id.nomPass3);
        final EditText passengerName4 = (EditText)findViewById(R.id.nomPass4);
        final EditText passengerWeight1 = (EditText)findViewById(R.id.idPoids1);
        final EditText passengerWeight2 = (EditText)findViewById(R.id.idPoids2);
        final EditText passengerWeight3 = (EditText)findViewById(R.id.idPoids3);
        final EditText passengerWeight4 = (EditText)findViewById(R.id.idPoids4);
        final EditText passengerBagages = (EditText) findViewById(R.id.idPoidsBagages);
        Button validation = (Button)findViewById(R.id.valider);
        final TextView result = (TextView)findViewById(R.id.idresult);


        validation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PassengerModel number1 = new PassengerModel(passengerName1.getText().toString(),Integer.valueOf(passengerWeight1.getText().toString()));
                PassengerModel number2 = new PassengerModel(passengerName2.getText().toString(),Integer.valueOf(passengerWeight2.getText().toString()));
                PassengerModel number3 = new PassengerModel(passengerName3.getText().toString(),Integer.valueOf(passengerWeight3.getText().toString()));
                PassengerModel number4 = new PassengerModel(passengerName4.getText().toString(),Integer.valueOf(passengerWeight4.getText().toString()));
                int bagagesWeight = Integer.valueOf(passengerBagages.getText().toString());

                myPlane.setPassager1(number1);
                myPlane.setPassager2(number2);
                myPlane.setPassager3(number3);
                myPlane.setPassager4(number4);
                myPlane.setBagagesWeight(bagagesWeight);

                result.setText(myPlane.calculOptimum());

            }
        });
    }
}
