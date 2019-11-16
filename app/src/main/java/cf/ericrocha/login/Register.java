package cf.ericrocha.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    EditText emailId, password, passwordC;
    Button btnSigUp;
    TextView tvLog;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFirebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.email_edt_r);
        password = findViewById(R.id.senha_edt_r);
        passwordC = findViewById(R.id.senhaC_edt_r);
        tvLog = findViewById(R.id.back_login);
        btnSigUp = findViewById(R.id.btn_register);


    }
    public void register(View v){
        String email = emailId.getText().toString();
        String pwd = password.getText().toString();
        String pwdC = passwordC.getText().toString();
        if (email.isEmpty()) {
            emailId.setError("Digite o email");
            emailId.requestFocus();
        } else if (pwd.isEmpty()) {
            password.setError("Digite a senha");
            password.requestFocus();
        } else if (pwdC.isEmpty()) {
            passwordC.setError("Confirme a senha");
            passwordC.requestFocus();
        } else if (!pwdC.equals(pwd)) {
            passwordC.setError("As senhas não coincidem ");
            passwordC.requestFocus();
        } else if (email.isEmpty() && pwd.isEmpty() && pwdC.isEmpty()) {
            Toast.makeText(Register.this, "Os campos estão vazios!", Toast.LENGTH_SHORT).show();

        } else if (!email.isEmpty() && !pwd.isEmpty() && !pwdC.isEmpty() && pwdC.equals(pwd)){
            mFirebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(!task.isSuccessful()){
                        Toast.makeText(Register.this, "Falha, favor tentar novamente!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(Register.this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Register.this, MainActivity.class));
                    }
                }
            });
        }
        else {
            Toast.makeText(Register.this, "Ocorreu um erro!", Toast.LENGTH_SHORT).show();
        }
    }

    public  void  back(View v){

        /*Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);*/
        startActivity(new Intent(this, MainActivity.class));
        finishAffinity();


    }

    @Override
    public void onBackPressed(){
        startActivity(new Intent(this, MainActivity.class));
        finishAffinity();
    }



}