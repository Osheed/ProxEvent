package com.train.proxevent;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.train.proxevent.Objects.Car;
import com.train.proxevent.Objects.FirebaseReferences;

public class Login extends AppCompatActivity {

    Button buttonCar ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //create button
        buttonCar = (Button) findViewById(R.id.button_car);

        //add firebase database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //add reference to database
        final DatabaseReference myRef = database.getReference(FirebaseReferences.PROXEVENT_REFERENCE);

        //add listener to button
        buttonCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get childs to create a new car
                Car car = new Car("ford","Cano",3,4);
                //give to create
                myRef.child(FirebaseReferences.CAR_REFERENCE).push().setValue(car);


            }
        });
/**for cars
        //get childs
        myRef.child(FirebaseReferences.CAR_REFERENCE).addValueEventListener(new ValueEventListener(){

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Car car = dataSnapshot.getValue(Car.class);
                //print
                //Log.i("CAR",car.toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

**/
     /**   //change value of database/ProxEvent to 4
        myRef.setValue(4);
        //add values to database/ProxEvent (not duplicates!!)
        myRef.push().setValue(5);
        myRef.push().setValue(9);
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int valor = dataSnapshot.getValue(Integer.class);
                Log.i("DATA:",valor + "");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("ERROR", databaseError.getMessage());
            }
        };
       myRef.addValueEventListener(valueEventListener);

      **/
      }
}
