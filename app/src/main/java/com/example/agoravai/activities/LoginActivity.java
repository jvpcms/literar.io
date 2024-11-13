package com.example.agoravai.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.agoravai.R;
import com.example.agoravai.services.RetrofitService;
import com.example.agoravai.services.LoginCallback;
import com.example.agoravai.models.NotAuthedUserEntity;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.editTextUsername);
        passwordEditText = findViewById(R.id.editTextPassword);
        loginButton = findViewById(R.id.buttonLogin);

        RetrofitService retrofitService = new RetrofitService(this);

        loginButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // Criar o objeto NotAuthedUserEntity
            NotAuthedUserEntity notAuthedUser = new NotAuthedUserEntity(username, password);

            // Passar o objeto NotAuthedUserEntity para o método loginUser
            retrofitService.loginUser(notAuthedUser, new LoginCallback() {
                @Override
                public void onSuccess(String token) {
                    // Código de sucesso
                }

                @Override
                public void onFailure(String errorMessage) {
                    // Código de falha
                }
            }, this); // Passando o contexto da Activity);
        });
    }
}
