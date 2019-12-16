package com.customdialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.john.customdialog.CustomDialog;
import com.john.customdialog.models.DialogContent;

public class MainActivity extends AppCompatActivity {

    Button button_standard_dialog;
    Button button_custom_dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_standard_dialog = findViewById(R.id.button_standard_dialog);
        button_standard_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createStandardDialog("Confirm", "Are you sure that you want to exit the application?");
            }
        });

        button_custom_dialog = findViewById(R.id.button_custom_dialog);
        button_custom_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createCustomDialog();
            }
        });
    }

    private void createStandardDialog(String title, String message) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        // set title
        alertDialogBuilder.setTitle(title);

        // set dialog message
        alertDialogBuilder
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void createCustomDialog() {
        final DialogContent content = new DialogContent();
        content.message = "Are you sure?";
        content.yesButtonText = "Proceed";
        content.noButtonText = "Cancel";
        content.title = "Warning";

        CustomDialog alertDialog = new CustomDialog(this, content, new CustomDialog.CustomDialogListener() {
            @Override
            public void onYesClicked(CustomDialog dialog) {
                Toast.makeText(MainActivity.this, content.yesButtonText + " clicked ", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }

            @Override
            public void onNoClicked(CustomDialog dialog) {
                Toast.makeText(MainActivity.this, content.noButtonText + " clicked ", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        alertDialog.show();
    }
}

