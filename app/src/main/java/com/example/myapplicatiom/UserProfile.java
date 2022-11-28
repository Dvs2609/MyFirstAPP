package com.example.myapplicatiom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
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
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class UserProfile extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseStorage storage;
    StorageReference storageReference;
    GoogleSignInClient mGoogleSignInClient;
    GoogleSignInOptions gso;
    TextView txtId, txtName, txtEmail;
    ImageView imageUser;
    public Uri imageUri;
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
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

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

        imageUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ChoosePicture();

            }
        });
    }
    private void getDownloadUrl(StorageReference reference){
        reference.getDownloadUrl()
                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Log.d("UserProfile", "onSuccess: " + uri);
                        UploadUser(imageUri);
                    }
                });
    }
    private void UploadUser(Uri uri) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        UserProfileChangeRequest request = new UserProfileChangeRequest.Builder()
                .setPhotoUri(uri)
                .build();
        user.updateProfile(request)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(UserProfile.this, "Usuario actualizado", Toast.LENGTH_SHORT ).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(UserProfile.this, "Usuario NO actualizado", Toast.LENGTH_SHORT ).show();

                    }
                });
    }

    private void ChoosePicture() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Resultado devuelto al iniciar el Intent de GoogleSignInApi.getSignInIntent (...);
        // Result returned from Launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == 1 && resultCode == RESULT_OK && data!=null && data.getData() != null) {
            imageUri = data.getData();
            imageUser.setImageURI(imageUri);
            uploadPicture();
        }
    }

    private void uploadPicture() {

        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Uploading Image...");
        pd.show();
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        final String randomKey = UUID.randomUUID().toString();
        StorageReference reference = storageReference.child("images/" + uid);

        reference.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            pd.dismiss();
                            Snackbar.make(findViewById(android.R.id.content),"Image Uploaded", Snackbar.LENGTH_LONG).show();
                            getDownloadUrl(reference);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            pd.dismiss();
                            Toast.makeText(getApplicationContext(), "Failed to upload", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                            double progressPercent = (100.00 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            pd.setMessage("Percentage: " + (int) progressPercent + "%");
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
                //        pubº
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