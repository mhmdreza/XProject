package com.example.mhmdreza_j.xproject.views.login;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mhmdreza_j.xproject.R;
import com.example.mhmdreza_j.xproject.utils.ActivityHelper;
import com.example.mhmdreza_j.xproject.views.base_class.BaseActivity;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class LoginActivity extends BaseActivity {
    private static final String REQUEST_ID_TOKEN = "504094211694-e4684isjt88ab0uca780u2taml9mqat6.apps.googleusercontent.com";
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    public static final int GOOGLE_PLAY_GAMES_RC = 2;
    public static final int GOOGLE_PLUS_RC = 1;
    private GoogleSignInClient googleSignInClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this);
        setContentView(R.layout.activity_login);


        Button facebookButton = findViewById(R.id.facebookLogo);
        Button googlePlusButton = findViewById(R.id.googlePlusLogo);
        Button googlePlayGamesButton = findViewById(R.id.googlePlayGamesLogo);
        Button playAsAGuest = findViewById(R.id.playAsGuestButton);
        playAsAGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityHelper.startMainActivity(getParent());
            }
        });
        googlePlayGamesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                initGooglePlayGamesLogin();
                startSignInIntent();
            }
        });
        googlePlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initGooglePlusLogin();
                signIn();
            }
        });

//        Button playAsGuestButton = findViewById(R.id.playAsGuestButton);
//        LoginManager.getInstance().logOut();
        callbackManager = CallbackManager.Factory.create();

        initFacebookLogin();
    }

    private void signIn() {
        Intent signInIntent = googleSignInClient.getSignInIntent();

        startActivityForResult(signInIntent, GOOGLE_PLUS_RC);
    }

    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null) {
            ActivityHelper.startMainActivity(this);
            Toast.makeText(this, "user logged in previously!!!!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        signInSilently();
    }

    private void signInSilently() {
        GoogleSignInClient signInClient = GoogleSignIn.getClient(this,
                GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN);
        signInClient.silentSignIn().addOnCompleteListener(this,
                new OnCompleteListener<GoogleSignInAccount>() {
                    @Override
                    public void onComplete(@NonNull Task<GoogleSignInAccount> task) {
                        if (task.isSuccessful()) {
                            // The signed in account is stored in the task's result.
                            GoogleSignInAccount signedInAccount = task.getResult();
                            ActivityHelper.startMainActivity(getParent());
                            Toast.makeText(LoginActivity.this, "WOOOOOOOOOOW!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, "Noooooooooooooo!", Toast.LENGTH_SHORT).show();
                            // Player will need to sign-in explicitly using via UI
                        }
                    }
                });
    }

    private void initGooglePlusLogin() {
        // Configure sign-in to request the user's ID, email address, and basic
// profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestIdToken(REQUEST_ID_TOKEN)
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, gso);

    }

    private void initGooglePlayGamesLogin() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN)
                .requestEmail()
                .requestIdToken(REQUEST_ID_TOKEN)
                .build();
        googleSignInClient =  GoogleSignIn.getClient(this,
                GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN);
        googleSignInClient.silentSignIn().addOnCompleteListener(this, new OnCompleteListener<GoogleSignInAccount>() {
            @Override
            public void onComplete(@NonNull Task<GoogleSignInAccount> task) {
                if (task.isSuccessful()){
                    ActivityHelper.startMainActivity(getParent());
                }
            }
        });
    }

    private void initFacebookLogin() {
        loginButton = findViewById(R.id.login_button);
//        loginButton.setReadPermissions(Arrays.asList(EMAIL));
//        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
        // If you are using in a fragment, call loginButton.setFragment(this);
//         Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(LoginActivity.this, "SUCCESS", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
                Toast.makeText(LoginActivity.this, "CANCEL", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Toast.makeText(LoginActivity.this, "ERROR!", Toast.LENGTH_SHORT).show();
            }
        });


        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        Toast.makeText(LoginActivity.this, "Success2!", Toast.LENGTH_SHORT).show();
                        ((TextView) findViewById(R.id.textView)).setText(loginResult.getAccessToken().toString() + " QQQQ \n\n" + loginResult.getAccessToken().getUserId());
                        Toast.makeText(LoginActivity.this, loginResult.getAccessToken().getUserId(), Toast.LENGTH_LONG).show();
                        Log.d("WWWWWWWWWWWWWWWWWWW", "onSuccess: " + loginResult.getAccessToken().getUserId());
                    }

                    @Override
                    public void onCancel() {
                        // App code
                        Toast.makeText(LoginActivity.this, "cancel2!", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                        Toast.makeText(LoginActivity.this, exception.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void startSignInIntent() {
        GoogleSignInClient signInClient = GoogleSignIn.getClient(this,
                GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN);
        Intent intent = signInClient.getSignInIntent();
        startActivityForResult(intent, GOOGLE_PLAY_GAMES_RC);
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            ActivityHelper.startMainActivity(this);
            Log.w("AAAAAAAAAAAAAAAAAAAA", "!@#$%^&*())(*&^%$#@!");
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, account.getId(), Toast.LENGTH_SHORT).show();
            Log.d("QQQQQQQQQQQQQQQQQQQQQ", "ID: " + account.getId());

//            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show();
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("AAAAAAAAAAAAAAAAAAAA", "signInResult:failed code=" + e.getStatusCode());
        }
    }

    private boolean isSignedIn() {
        return GoogleSignIn.getLastSignedInAccount(this) != null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GOOGLE_PLUS_RC) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
        else if (requestCode == GOOGLE_PLAY_GAMES_RC){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // The signed in account is stored in the result.
                GoogleSignInAccount signedInAccount = result.getSignInAccount();
            } else {

                String message = result.getStatus().getStatusMessage();
                if (message == null || message.isEmpty()) {
                    message = "error: " + result.getStatus().getStatusCode();
                }
//                else if (message.isEmpty()) {
////                    message = getString(R.string.signin_other_error);
//                    message = "signin_other_error";
//                }
                new AlertDialog.Builder(this).setMessage(message)
                        .setNeutralButton(android.R.string.ok, null).show();
            }
        }
    }
}
