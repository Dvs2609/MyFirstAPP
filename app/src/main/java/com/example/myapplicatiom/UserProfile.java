package com.example.myapplicatiom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class UserProfile extends AppCompatActivity {

    FirebaseAuth mAuth;
    GoogleSignInClient mGoogleSignInClient;
    GoogleSignInOptions gso;
    TextView txtId, txtName, txtEmail;
    ImageView imageUser;
    Button logOut, deleteUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        txtId = findViewById(R.id.textId);
        txtName = findViewById(R.id.textName);
        txtEmail = findViewById(R.id.textEmail);
        imageUser = findViewById(R.id.imageUser);
        logOut = findViewById(R.id.btLogOut);
        deleteUser = findViewById(R.id.btDeleteUser);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        txtId.setText(currentUser.getUid());
        txtName.setText(currentUser.getDisplayName());
        txtEmail.setText(currentUser.getEmail());
        Glide.with(this).load(currentUser.getPhotoUrl()).into(imageUser);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id2))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                mGoogleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Intent MainActivity2 = new Intent(getApplicationContext(), MainActivity2.class);
                            startActivity(MainActivity2);
                            UserProfile.this.finish();
                        }else{
                            Toast.makeText(getApplicationContext(),"No se pudo cerrar Sesión", Toast.LENGTH_SHORT);
                        }
                    }
                });

            }
        });


        //Antes de Eliminar se debe re-autenticar el usuario con el token/credenciales
        deleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Obtener usuario actualº
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                //FirebaseAuth.getInstance().deleteUser(uid);


                //Obtener Cuenta
                GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(getApplicationContext());

                if (signInAccount != null){
                    AuthCredential credential = GoogleAuthProvider.getCredential(signInAccount.getIdToken(),null);
                    //Re-autenticar el usuario
                    user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            //Eliminar Usuario
                            if (task.isSuccessful()) {
                                user.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Log.d("UserProfile", "onSucces:Usuario Eliminado");

                                        signOut();
                                    }
                                });
                            }else{

                                Log.e("UserProfile", "onComplete: Error al eliminar el usuario", task.getException());
                            }
                        }
                    });
                }else{
                    //GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
                    String currentUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    deleteUser(currentUid);
                    Log.d("UserProfile","Error: reAuthUser: user account null");
                }
            }
        });
    }

    private void deleteUser(String uid) {

        // [START reauthenticate]
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        //Insetar credenciales automaticamente?
        AuthCredential credential = EmailAuthProvider.getCredential("atos@gmail.com", "1234asdf");
        // Prompt the user to re-provide their sign-in credentials
        //if(user != null) {
          //  user.reauthenticate(credential)
            //        .addOnCompleteListener(new OnCompleteListener<Void>() {
              //          @Override
                //        public void onComplete(@NonNull Task<Void> task) {
                            user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Log.d("UserProfile", "User account deleted.");
                                        Intent intent = new Intent(UserProfile.this, MainActivity2.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                        Toast.makeText(UserProfile.this, "Deleted User Successfully,", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                       // }
                  //  });
            // [END reauthenticate]
       // }
    }

    private void signOut(){
        //Sign-Out Firebase
        FirebaseAuth.getInstance().signOut();
        mGoogleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Intent MainActivity2 = new Intent(getApplicationContext(), MainActivity2.class);
                MainActivity2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(MainActivity2);
                UserProfile.this.finish();
            }
        });

    }
}