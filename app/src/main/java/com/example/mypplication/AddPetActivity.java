package com.example.mypplication;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.UUID;

public class AddPetActivity extends AppCompatActivity {
    private static final int RESULT_LOAD_IMG = 1;
    private static final String[] GENDERS = {"MALE", "FEMALE"};
    private static final String[] AGE_TYPES = {"Months", "Years"};

    private EditText mNameET;
    private EditText mDescriptionET;
    private Button mSelectAgeBtn;
    private MaterialButton mSelectGenderBtn;
    private ConstraintLayout mSelectPetImageBtn;
    private ImageView mPetIV;
    private ConstraintLayout mContentImageLayout;

    private StorageReference mStorageReference;
    private DatabaseReference mDatabaseReference;

    private String mUserId = "a1s2d3f45g677y65";

    private Uri mImageUri;
    private String mAge;
    private String mGender;
    private int mAgeType;
    private String mName;
    private String mDescription;
    private String mImageUrl;

    private boolean mIsUploading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pet);

        mUserId = FirebaseAuth.getInstance().getUid();
        if (mUserId != null && !mUserId.isEmpty()) {
            initView();
            FirebaseStorage storage = FirebaseStorage.getInstance();
            mStorageReference = storage.getReference();

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            mDatabaseReference = database.getReference();
        } else {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void initView() {
        ImageView backBtn = findViewById(R.id.back_btn);
        mNameET = findViewById(R.id.pet_name_text_view);
        mDescriptionET = findViewById(R.id.pet_description_text_view);
        mSelectAgeBtn = findViewById(R.id.select_age_btn);
        mSelectGenderBtn = findViewById(R.id.select_gender_btn);
        mSelectPetImageBtn = findViewById(R.id.select_image_btn);
        mPetIV = findViewById(R.id.pet_image_view);
        LinearLayout replaceBtn = findViewById(R.id.replace_btn);
        Button postPetBtn = findViewById(R.id.post_pet_btn);
        mContentImageLayout = findViewById(R.id.content_image_layout);

        backBtn.setOnClickListener(b -> {
            finish();
        });
        mSelectGenderBtn.setOnClickListener(b -> {
            selectGender();
        });
        mSelectAgeBtn.setOnClickListener(b -> {
            selectAge();
        });
        mSelectPetImageBtn.setOnClickListener(b -> {
            chooseImage();
        });
        replaceBtn.setOnClickListener(b -> {
            chooseImage();
        });
        postPetBtn.setOnClickListener(b -> {
            postPetData();
        });
    }

    private void selectAge() {
        Dialog dialog = new Dialog(this, R.style.MyAlertDialogTheme);
        dialog.setContentView(R.layout.set_age_dialog);
        Button okBtn = dialog.findViewById(R.id.ok_btn);
        Button cancelBtn = dialog.findViewById(R.id.cancel_btn);
        EditText ageET = dialog.findViewById(R.id.age_edit_text);
        RadioGroup radioGroup = dialog.findViewById(R.id.radio_group);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.months_radio_btn) {
                    mAgeType = 0;
                } else if (i == R.id.years_radio_btn) {
                    mAgeType = 1;
                }
            }
        });
        cancelBtn.setOnClickListener(b -> {
            dialog.dismiss();
        });
        okBtn.setOnClickListener(b -> {
            String input = ageET.getText().toString();
            if (input.isEmpty()) {
                Toast.makeText(AddPetActivity.this, "Age field is empty!", Toast.LENGTH_SHORT).show();
            } else {
                mAge = input + " " + AGE_TYPES[mAgeType];
                mSelectAgeBtn.setText(mAge);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void postPetData() {
        mName = getInputText(mNameET);
        mDescription = getInputText(mDescriptionET);
        if (mName == null) {
            Toast.makeText(this, "Name field is empty!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mDescription == null) {
            Toast.makeText(this, "Description field is empty!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mAge == null) {
            Toast.makeText(this, "Choose pet age!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mGender == null) {
            Toast.makeText(this, "Choose pet gender!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mImageUri == null) {
            Toast.makeText(this, "Choose Image First", Toast.LENGTH_SHORT).show();
            setErrorBackground();
            return;
        }
        uploadPost();
    }

    @SuppressLint("RestrictedApi")
    private void selectGender() {
        MenuBuilder menuBuilder = new MenuBuilder(this);
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.menu, menuBuilder);
        MenuPopupHelper optionsMenu = new MenuPopupHelper(this, menuBuilder, mSelectGenderBtn);
        optionsMenu.setForceShowIcon(true);
        menuBuilder.setCallback(new MenuBuilder.Callback() {
            @Override
            public boolean onMenuItemSelected(@NonNull MenuBuilder menu, @NonNull MenuItem item) {
                int resId;
                boolean result;
                switch (item.getItemId()) {
                    case R.id.male_option:
                        mGender = GENDERS[0];
                        resId = R.drawable.baseline_male_black_24dp;
                        result = true;
                        break;
                    case R.id.female_option:
                        mGender = GENDERS[1];
                        resId = R.drawable.baseline_female_black_24dp;
                        result = true;
                        break;
                    default:
                        resId = R.drawable.baseline_male_black_24dp;
                        result = false;
                        break;
                }
                mSelectGenderBtn.setIconResource(resId);
                mSelectGenderBtn.setText(mGender);
                return result;
            }

            @Override
            public void onMenuModeChange(@NonNull MenuBuilder menu) {
            }
        });
        optionsMenu.show();
    }

    private void chooseImage() {
        mImageUri = null;
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            try {
                mImageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(mImageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                loadImage(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(AddPetActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(AddPetActivity.this, "You haven't picked Image", Toast.LENGTH_LONG).show();
        }
    }

    private void loadImage(Bitmap bitmap) {
        mPetIV.setImageBitmap(bitmap);
        mSelectPetImageBtn.setVisibility(View.GONE);
        mContentImageLayout.setVisibility(View.VISIBLE);
    }

    private void uploadPost() {
        mIsUploading = true;
        ProgressDialog progressDialog = new ProgressDialog(this, R.style.ProgressDialogTheme);
        progressDialog.setTitle("Posting...");
        progressDialog.setMessage("Posting in progress...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        StorageReference ref = mStorageReference
                .child("pet_images/" + UUID.randomUUID().toString());
        ref.putFile(mImageUri)
                .addOnSuccessListener(
                        taskSnapshot -> {
                            taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    if (task.isSuccessful()) {
                                        mImageUrl = task.getResult().toString();
                                        uploadToDatabase(progressDialog);
                                    } else {
                                        mIsUploading = false;
                                        progressDialog.dismiss();
                                        Toast.makeText(AddPetActivity.this, "Something went wrong, retry again!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        })
                .addOnFailureListener(e -> {
                    mIsUploading = false;
                    progressDialog.dismiss();
                    Toast.makeText(AddPetActivity.this, "Something went wrong, retry again!", Toast.LENGTH_SHORT).show();
                });
    }

    private void uploadToDatabase(ProgressDialog dialog) {
        Post post = new Post(mUserId, mName, mDescription, mImageUrl, mAge, mGender);
        mDatabaseReference.child("posts").child(UUID.randomUUID().toString())
                .setValue(post)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        mIsUploading = false;
                        dialog.dismiss();
                        if (task.isSuccessful()) {
                            Toast.makeText(AddPetActivity.this, "Post published successfully", Toast.LENGTH_SHORT).show();
                            AddPetActivity.this.finish();
                        } else {
                            Toast.makeText(AddPetActivity.this, "Something went wrong, retry again!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void setErrorBackground() {
        mSelectPetImageBtn.setBackgroundResource(R.drawable.add_img_err_bg);
        String color = "#FF3344";
        ((ImageView) findViewById(R.id.add_img_icon)).setColorFilter(Color.parseColor(color));
        ((TextView) findViewById(R.id.select_image_text_view)).setTextColor(Color.parseColor(color));
    }

    private String getInputText(EditText editText) {
        String input = editText.getText().toString();
        if (!input.isEmpty()) {
            return input;
        }
        return null;
    }

    @Override
    public void onBackPressed() {
        if (mIsUploading) {
            Toast.makeText(this, "Wait until posting finish", Toast.LENGTH_SHORT).show();
            return;
        }
        super.onBackPressed();
    }
}