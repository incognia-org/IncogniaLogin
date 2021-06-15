package com.incognia.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.incognia.login.database.Database;

import java.util.UUID;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        String user = Database.getLoggedUser(getApplicationContext());
        TextView txtWelcome = (TextView) findViewById(R.id.txtWelcome);
        txtWelcome.setText(user);

        Button btnLogout = (Button) findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Database.logout(getApplicationContext());
                Intent goToNextActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(goToNextActivity);
            }
        });

        Button btnTransaction = (Button) findViewById(R.id.btnTransaction);
        btnTransaction.setOnClickListener(this);

    }

    private void doTransaction() {
        UUID uuid = UUID.randomUUID();
        String transactionId = uuid.toString();
        String msg = "Transaction " + transactionId.substring(0, 5) + " successful!";
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(final View v) {
        doTransaction();
    }
}