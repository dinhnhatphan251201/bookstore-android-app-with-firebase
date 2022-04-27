package vn.edu.iuh.fit.se.android.dhktpm15ctt.nhom10.toannhat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    TextView txtLoginLink;
    EditText edtFullName, edtEmail, edtPassword, edtConfirmPassword;
    Button btnRegister;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();

        mapping();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

        txtLoginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

    }

    private void register() {
        String fullName = edtFullName.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        String confirmPassword = edtConfirmPassword.getText().toString().trim();

        if(TextUtils.isEmpty(fullName) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)){
            Toast.makeText(RegisterActivity.this, "Vui lòng điền day đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!password.equals(confirmPassword)) {
            Toast.makeText(RegisterActivity.this, "Mật khẩu không khớp! Vui lòng nhập lại", Toast.LENGTH_SHORT).show();
            return;
        }

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(RegisterActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                }else {
                    Toast.makeText(RegisterActivity.this, "Đắng ký thất bại!!! Vui lòng thử lại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void mapping() {
        txtLoginLink = findViewById(R.id.txtLoginLink_RegisterScreen);
        edtFullName = findViewById(R.id.edtFullName_RegisterScreen);
        edtEmail = findViewById(R.id.edtEmail_RegisterScreen);
        edtPassword = findViewById(R.id.edtPassword_RegisterScreen);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword_RegisterScreen);
        btnRegister = findViewById(R.id.btnRegister_RegisterScreen);
    }
}