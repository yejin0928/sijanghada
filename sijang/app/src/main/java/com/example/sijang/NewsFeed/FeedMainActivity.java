/*
 * Copyright 2016 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.sijang.NewsFeed;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.Request;
import com.example.sijang.R;
import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;

import androidx.annotation.NonNull;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.core.content.FileProvider;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.vision.v1.Vision;
import com.google.api.services.vision.v1.VisionRequest;
import com.google.api.services.vision.v1.VisionRequestInitializer;
import com.google.api.services.vision.v1.model.AnnotateImageRequest;
import com.google.api.services.vision.v1.model.BatchAnnotateImagesRequest;
import com.google.api.services.vision.v1.model.BatchAnnotateImagesResponse;
import com.google.api.services.vision.v1.model.EntityAnnotation;
import com.google.api.services.vision.v1.model.Feature;
import com.google.api.services.vision.v1.model.Image;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class FeedMainActivity extends AppCompatActivity {

    private static final String CLOUD_VISION_API_KEY = "AIzaSyDH0Z1QmQVGEM5TIID8yGxure0fkG1OiLU";
    public static final String FILE_NAME = "temp.jpg";
    private static final String ANDROID_CERT_HEADER = "X-Android-Cert";
    private static final String ANDROID_PACKAGE_HEADER = "X-Android-Package";
    private static final int MAX_LABEL_RESULTS = 10;
    private static final int MAX_DIMENSION = 1200;

    private static final String TAG = FeedMainActivity.class.getSimpleName();
    private static final int GALLERY_PERMISSIONS_REQUEST = 0;
    private static final int GALLERY_IMAGE_REQUEST = 1;
    public static final int CAMERA_PERMISSIONS_REQUEST = 2;
    public static final int CAMERA_IMAGE_REQUEST = 3;
    private EditText t1;
    private TextView mImageDetails;
    private ImageView mMainImage;
    public static Handler UIHandler;
    public static int finish=0;
    public static int count=0;
    private static CheckBox cb1;
    private static CheckBox cb2;
    private static CheckBox cb3;
    private static CheckBox cb4;
    private static CheckBox cb5;
    private static CheckBox cb6;
    private static CheckBox cb7;
    private static CheckBox cb8;
    private static CheckBox cb9;
    private static CheckBox cb10;
    private static ArrayList<String> list1 = new ArrayList<>();
    private static ArrayList<String> product_list = new ArrayList<>();
    public static ArrayList<String> arr=new ArrayList<String>();
    public static HashMap<String,String> tag= new HashMap<String,String>();

    public String result_s="";
    public byte[] imageBytes;

    static
    {
        UIHandler = new Handler(Looper.getMainLooper());
    }
    public static void runOnUI(Runnable runnable) {
        UIHandler.post(runnable);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feed_activity_main);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(FeedMainActivity.this);
            builder
                    .setMessage(R.string.dialog_select_prompt)
                    .setPositiveButton(R.string.dialog_select_gallery, (dialog, which) -> startGalleryChooser())
                    .setNegativeButton(R.string.dialog_select_camera, (dialog, which) -> startCamera());
            builder.create().show();
        });

        mImageDetails = findViewById(R.id.image_details);
        mMainImage = findViewById(R.id.main_image);
        cb1 = findViewById(R.id.checkBox1);
        cb2 = (CheckBox)findViewById(R.id.checkBox2);
        cb3 = (CheckBox)findViewById(R.id.checkBox3);
        cb4 = (CheckBox)findViewById(R.id.checkBox4);
        cb5 = (CheckBox)findViewById(R.id.checkBox5);
        cb6 = (CheckBox)findViewById(R.id.checkBox6);
        cb7 = (CheckBox)findViewById(R.id.checkBox7);
        cb8 = (CheckBox)findViewById(R.id.checkBox8);
        cb9 = (CheckBox)findViewById(R.id.checkBox9);
        cb10 = (CheckBox)findViewById(R.id.checkBox10);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        imageBytes=stream.toByteArray();

        Button b = (Button)findViewById(R.id.button1);
        Button b2 = (Button) findViewById(R.id.button2) ;
        b2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(FeedMainActivity.this, FeedActivity.class);
                myIntent.putExtra("hash", result_s);
                myIntent.putExtra("image", imageBytes);
                startActivity(myIntent);
            }
        });
        final TextView tv = (TextView)findViewById(R.id.textView2);

        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String result = "";  // 결과를 출력할 문자열  ,  항상 스트링은 빈문자열로 초기화 하는 습관을 가지자
                if(cb1.isChecked() == true) result += cb1.getText().toString();
                if(cb2.isChecked() == true) result += cb2.getText().toString();
                if(cb3.isChecked() == true) result += cb3.getText().toString();
                if(cb4.isChecked() == true) result += cb4.getText().toString();
                if(cb5.isChecked() == true) result += cb5.getText().toString();
                if(cb6.isChecked() == true) result += cb6.getText().toString();
                if(cb7.isChecked() == true) result += cb7.getText().toString();
                if(cb8.isChecked() == true) result += cb8.getText().toString();
                if(cb9.isChecked() == true) result += cb9.getText().toString();
                if(cb10.isChecked() == true) result += cb10.getText().toString();
                count++;
                if((count+1)%3==0)
                    result+="\n";
                result_s+=result;
                tv.setText("선택결과:" +"\n"+ result);

            } // end onClick
        }); // end setOnClickListener
    }

    public void startGalleryChooser() {
        if (PermissionUtils.requestPermission(this, GALLERY_PERMISSIONS_REQUEST, Manifest.permission.READ_EXTERNAL_STORAGE)) {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //your code or your request that you want to run on uiThread
                            Intent intent = getIntent();
                            intent.setType("image/*");
                            intent.setAction(Intent.ACTION_GET_CONTENT);
                            startActivityForResult(Intent.createChooser(intent, "Select a photo"),
                                    GALLERY_IMAGE_REQUEST);
                        }
                    });


        }
    }

    public void startCamera() {
        if (PermissionUtils.requestPermission(
                this,
                CAMERA_PERMISSIONS_REQUEST,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA)) {


            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            Uri photoUri = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", getCameraFile());
            new Thread(new Runnable() {


                @Override
                public void run() {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //your code or your request that you want to run on uiThread
                            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                            startActivityForResult(intent, CAMERA_IMAGE_REQUEST);
                        }
                    });
                }
            }).start();

        }
    }

    public File getCameraFile() {

        File dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return new File(dir, FILE_NAME);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            uploadImage(data.getData());
        } else if (requestCode == CAMERA_IMAGE_REQUEST && resultCode == RESULT_OK) {
            Uri photoUri = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", getCameraFile());
            uploadImage(photoUri);
        }
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CAMERA_PERMISSIONS_REQUEST:
                if (PermissionUtils.permissionGranted(requestCode, CAMERA_PERMISSIONS_REQUEST, grantResults)) {
                    startCamera();
                }
                break;
            case GALLERY_PERMISSIONS_REQUEST:
                if (PermissionUtils.permissionGranted(requestCode, GALLERY_PERMISSIONS_REQUEST, grantResults)) {
                    startGalleryChooser();
                }
                break;
        }
    }

    public void uploadImage(Uri uri) {
        if (uri != null) {
            try {
                // scale the image to save on bandwidth
                Bitmap bitmap =
                        scaleBitmapDown(
                                MediaStore.Images.Media.getBitmap(getContentResolver(), uri),
                                MAX_DIMENSION);

                callCloudVision(bitmap);
                mMainImage.setImageBitmap(bitmap);

            } catch (IOException e) {
                Log.d(TAG, "Image picking failed because " + e.getMessage());
                Toast.makeText(this, R.string.image_picker_error, Toast.LENGTH_LONG).show();
            }
        } else {
            Log.d(TAG, "Image picker gave us a null image.");
            Toast.makeText(this, R.string.image_picker_error, Toast.LENGTH_LONG).show();
        }
    }

    private Vision.Images.Annotate prepareAnnotationRequest(Bitmap bitmap) throws IOException {
        HttpTransport httpTransport = AndroidHttp.newCompatibleTransport();
        JsonFactory jsonFactory = GsonFactory.getDefaultInstance();

        VisionRequestInitializer requestInitializer =
                new VisionRequestInitializer(CLOUD_VISION_API_KEY) {
                    /**
                     * We override this so we can inject important identifying fields into the HTTP
                     * headers. This enables use of a restricted cloud platform API key.
                     */
                    @Override
                    protected void initializeVisionRequest(VisionRequest<?> visionRequest)
                            throws IOException {
                        super.initializeVisionRequest(visionRequest);

                        String packageName = getPackageName();
                        visionRequest.getRequestHeaders().set(ANDROID_PACKAGE_HEADER, packageName);

                        String sig = PackageManagerUtils.getSignature(getPackageManager(), packageName);

                        visionRequest.getRequestHeaders().set(ANDROID_CERT_HEADER, sig);
                    }
                };

        Vision.Builder builder = new Vision.Builder(httpTransport, jsonFactory, null);
        builder.setVisionRequestInitializer(requestInitializer);

        Vision vision = builder.build();

        BatchAnnotateImagesRequest batchAnnotateImagesRequest =
                new BatchAnnotateImagesRequest();
        batchAnnotateImagesRequest.setRequests(new ArrayList<AnnotateImageRequest>() {{
            AnnotateImageRequest annotateImageRequest = new AnnotateImageRequest();

            // Add the image
            Image base64EncodedImage = new Image();
            // Convert the bitmap to a JPEG
            // Just in case it's a format that Android understands but Cloud Vision
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, byteArrayOutputStream);


            imageBytes = byteArrayOutputStream.toByteArray();

            // Base64 encode the JPEG
            base64EncodedImage.encodeContent(imageBytes);
            annotateImageRequest.setImage(base64EncodedImage);

            // add the features we want
            annotateImageRequest.setFeatures(new ArrayList<Feature>() {{
                Feature labelDetection = new Feature();
                labelDetection.setType("LABEL_DETECTION");
                labelDetection.setMaxResults(MAX_LABEL_RESULTS);
                add(labelDetection);
            }});

            // Add the list of one thing to the request
            add(annotateImageRequest);
        }});

        Vision.Images.Annotate annotateRequest =
                vision.images().annotate(batchAnnotateImagesRequest);
        // Due to a bug: requests to Vision API containing large images fail when GZipped.
        annotateRequest.setDisableGZipContent(true);
        Log.d(TAG, "created Cloud Vision request object, sending request");

        return annotateRequest;
    }

    private class LableDetectionTask extends AsyncTask<Object, Void, String> {
        private final WeakReference<FeedMainActivity> mActivityWeakReference;
        private Vision.Images.Annotate mRequest;

        LableDetectionTask(FeedMainActivity activity, Vision.Images.Annotate annotate) {
            mActivityWeakReference = new WeakReference<>(activity);
            mRequest = annotate;
        }

        @Override
        protected String doInBackground(Object... params) {
            try {
                Log.d(TAG, "created Cloud Vision request object, sending request");
                BatchAnnotateImagesResponse response = mRequest.execute();

                return convertResponseToString(response);

            } catch (GoogleJsonResponseException e) {
                Log.d(TAG, "failed to make API request because " + e.getContent());
            } catch (IOException e) {
                Log.d(TAG, "failed to make API request because of other IOException " +
                        e.getMessage());
            }
            return "Cloud Vision API request failed. Check logs for details.";
        }

        protected void onPostExecute(String result) {
            FeedMainActivity activity = mActivityWeakReference.get();
            if (activity != null && !activity.isFinishing()) {
                TextView imageDetail = activity.findViewById(R.id.image_details);
                imageDetail.setText(result);
            }
        }
    }

    private void callCloudVision(final Bitmap bitmap) {
        // Switch text to loading
        mImageDetails.setText(R.string.loading_message);

        // Do the real work in an async task, because we need to use the network anyway
        try {
            AsyncTask<Object, Void, String> labelDetectionTask = new LableDetectionTask(this, prepareAnnotationRequest(bitmap));
            labelDetectionTask.execute();
        } catch (IOException e) {
            Log.d(TAG, "failed to make API request because of other IOException " +
                    e.getMessage());
        }
    }

    private Bitmap scaleBitmapDown(Bitmap bitmap, int maxDimension) {

        int originalWidth = bitmap.getWidth();
        int originalHeight = bitmap.getHeight();
        int resizedWidth = maxDimension;
        int resizedHeight = maxDimension;

        if (originalHeight > originalWidth) {
            resizedHeight = maxDimension;
            resizedWidth = (int) (resizedHeight * (float) originalWidth / (float) originalHeight);
        } else if (originalWidth > originalHeight) {
            resizedWidth = maxDimension;
            resizedHeight = (int) (resizedWidth * (float) originalHeight / (float) originalWidth);
        } else if (originalHeight == originalWidth) {
            resizedHeight = maxDimension;
            resizedWidth = maxDimension;
        }
        return Bitmap.createScaledBitmap(bitmap, resizedWidth, resizedHeight, false);
    }

    private  String convertResponseToString(BatchAnnotateImagesResponse response) {
        StringBuilder message = new StringBuilder("");

        List<EntityAnnotation> labels = response.getResponses().get(0).getLabelAnnotations();
        if (labels != null) {
            for (EntityAnnotation label : labels) {
                //message.append(String.format(Locale.US, "%.3f: %s", label.getScore(), label.getDescription()));
                temp(label.getDescription().toString());
                //list1.add(label.getDescription().toString());
               // message.append("\n");
            }
            tagg();
        } else {
            message.append("nothing");
        }


        return message.toString();
    }
    private static void temp(String s){
        list1.add(s);
        System.out.println("test::"+arr);

    }

    private void tagg() {


        product_list.add("Fish");
        product_list.add("Fruit");
        product_list.add("Clothing");
        product_list.add("Red meat");
        product_list.add("Dish");
        product_list.add("Vegetable");



        String[][] list = new String[][]{
                {"#신선해요", "#맛보장", "#오늘들어왔어요", "#제철", "#산지직송", "#국내산", "#싱싱해요", "#반찬으로딱!", "#할인행사", "#실해요"},
                {"#달아요", "#과일로기분전환", "#싱싱해요", "#오늘들어왔어요", "#맛보장", "#산지직송", "#상큼해요", "#할인행사", "#비타민뿜뿜", "#새콤달콤"},
                {"#떨이상품", "#세일상품", "#최고급원단", "#질보장", "#최저가", "#인기상품", "#봄용", "#여름용", "#가을용", "#겨울용", "#따뜻해요", "#시원해요", "#통풍굳", "#시즌오프세일", "#세트할인", "#B급상품세일"},
                {"#최상급", "#생고기", "#냉동", "#마블링", "#기름기적음", "#한우", "#단백질보충", "#몸보신", "#녹색축산", "#무항생제"},
                {"#맛보장", "#대표메뉴", "#베스트셀러", "#사장님추천메뉴", "#혜자상품", "#스테디셀러", "#국내산재료사용", "#약간매움", "#간식추천", "#기본메뉴", "#세트할인"},
                {"#다이어트", "#산지직송", "#직접재배", "#쌈채소", "#변비완화", "#항암효과", "#피부미용", "#건강에최고", "#비타민", "#칼슘퓽부"},
                {"#효자지하생선집", "#효자청과마트", "#통인옷가게", "#송림정육점", "#푸른마트", "#현대한우전문점", "#개성상회", "#호남상회", "#고기백화점", "#BBQ올리브떡볶이"}
        };

        ArrayList<String[]> list2 = new ArrayList<String[]>();

        for (int i = 0; i < 7; i++)
            list2.add(new String[]{list[i][0], list[i][1], list[i][2],
                    list[i][3], list[i][4], list[i][5], list[i][6], list[i][7], list[i][8], list[i][9]});

        //System.out.println(list1);

        // System.out.println(list1);
        //System.out.print(list2);


                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            int j=0;
                            while(j<list1.size()) {
                                for (int k = 0; k < 6; k++) {

                                    if (list1.get(j).equals(product_list.get(k))) {
                                        if(finish!=0)
                                            break;
                                        //System.out.println("*******"+list1.get(j)+"******"+product_list.get(k));
                                        // UI 코드를 이 안으로 옮긴다.
                                        cb1.setText(list[k][0]);
                                        cb2.setText(list[k][1]);
                                        cb3.setText(list[k][2]);
                                        cb4.setText(list[k][3]);
                                        cb5.setText(list[k][4]);
                                        cb6.setText(list[k][5]);
                                        cb7.setText(list[k][6]);
                                        cb8.setText(list[k][7]);
                                        cb9.setText(list[k][8]);
                                        cb10.setText(list[k][9]);
                                        finish++;
                                        //System.out.println("**********************");
                                    }
                                }
                                j++;
                            }

                        }


                    });
                    }




}
