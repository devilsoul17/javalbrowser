package com.santhosh.browser_app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.webkit.WebSettingsCompat;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.speech.RecognizerIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.URLUtil;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_SPEECH_INPUT = 1000;
    ImageButton btnrefresh, btnmic, searchbtn, back;
    ImageButton more, forward, stop, refbtn, home, incognito, btnbookmark;
    Button btntvForTab;
    EditText edittexturl;
    WebView webView;
    Button scanqr;
    ProgressBar progressBar;
    private int desktopSiteValue = 0;
    MydbHandler mydbHandler;
    MydbHandlerBookmark mydbHandlerBookmark;
    boolean dark = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_bg));

        webView = findViewById(R.id.webView);
        btnmic = findViewById(R.id.btnmic);
        btnrefresh = findViewById(R.id.btnrefresh);
        edittexturl = findViewById(R.id.edtTextUrl);
        searchbtn = findViewById(R.id.searchbtn);
        back = (ImageButton) findViewById(R.id.back);
        forward = (ImageButton) findViewById(R.id.forward);
        stop = (ImageButton) findViewById(R.id.stop);
        refbtn = (ImageButton) findViewById(R.id.refbtn);
        home = (ImageButton) findViewById(R.id.home);
//        incognito = (ImageButton) findViewById(R.id.incognito);
        more = (ImageButton) findViewById(R.id.more);
        btntvForTab = findViewById(R.id.btnTextViewForTab);
        btnbookmark = findViewById(R.id.btnAddtobookmarks);
        progressBar = findViewById(R.id.ProgressBar);

        mydbHandler = new MydbHandler(this, null ,null , 1);
        mydbHandlerBookmark =new MydbHandlerBookmark(this, null, null,1);
        saveData();


        progressBar.setMax(100);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setDisplayZoomControls(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setSupportMultipleWindows(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.loadUrl("https://google.com");

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                progressBar.setVisibility(View.VISIBLE);
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                saveData();
                progressBar.setVisibility(View.VISIBLE);
            }
        });

        progressBar.setProgress(0);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                progressBar.setProgress(newProgress);
                if (newProgress == 100)
                    progressBar.setVisibility(View.INVISIBLE);
                else
                    progressBar.setVisibility(View.VISIBLE);
                super.onProgressChanged(view, newProgress);
            }
        });
// Webview
        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!edittexturl.getText().toString().isEmpty()) {
                    String address = edittexturl.getText().toString();
                    if (address.contains("http") || address.contains("https")) {
                        if (address.contains(".com") || address.contains(".net") || address.contains(".in")) {
                            webView.loadUrl(address);
                        } else {
                            webView.loadUrl("https://www.google.com/search?q=" + address.replace("http", "").replace("https", ""));
                        }
                    } else {
                        if (address.contains(".com") || address.contains(".net") || address.contains(".in")) {
                            webView.loadUrl("http://" + address);

                        } else {
                            webView.loadUrl("https://www.google.com/search?q=" + address.replace("http", "").replace("https", ""));
                        }
                    }
                }
            }
        });
// More option
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog sheetDialog = new BottomSheetDialog(MainActivity.this,
                        R.style.BottomSheetDialogTheme);
                View sheetView = LayoutInflater.from(getApplicationContext())
                        .inflate(R.layout.reorderdialog, (LinearLayout) findViewById(R.id.dialog_container));
                sheetView.findViewById(R.id.filemenucancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sheetDialog.dismiss();
                    }
                });

                sheetView.findViewById(R.id.refresh).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        webView.reload();
                        Toast.makeText(MainActivity.this, "Refresh", Toast.LENGTH_SHORT).show();
                    }
                });

                sheetView.findViewById(R.id.history).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent his = new Intent(MainActivity.this, history.class);
                        startActivity(his);
                    }
                });

                sheetView.findViewById(R.id.scanQr).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MainActivity.this,ScanQrCode.class);
                    }
                });
//------------------------------------Settings--------------------------------------------------------------------//

             //------------------------------BOOKMARKS---------------------------------//

                sheetView.findViewById(R.id.favourite).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent book = new Intent(MainActivity.this, bookmarks.class);
                        startActivity(book);

                    }
                });

                sheetView.findViewById(R.id.download).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MainActivity.this,downloads.class);
                        startActivity(intent);
                    }
                });

                //=================darkmode------------------------//

                sheetView.findViewById ( R.id.lightdark ).setOnClickListener ( new View.OnClickListener () {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "Dark mode enabled", Toast.LENGTH_SHORT).show();
                        if (dark) {
                            WebSettingsCompat.setForceDark(webView.getSettings(), WebSettingsCompat.FORCE_DARK_OFF);
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                            dark = true;
                        } else {
                            WebSettingsCompat.setForceDark(webView.getSettings(), WebSettingsCompat.FORCE_DARK_ON);
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                            dark = true;
                        }
                    }
                } );
//-------------------------------Desktop site--------------------------------------------------//
                sheetView.findViewById(R.id.desktop_site).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Toast.makeText(MainActivity.this, "Desktopsite Enabled", Toast.LENGTH_SHORT).show();
                        if (desktopSiteValue == 0) {
                            setDesktopSite(webView, true);
                            desktopSiteValue = 1;
                        } else {
                            setDesktopSite(webView, false);
                            desktopSiteValue = 0;
                        }
                    }
                });

                sheetView.findViewById(R.id.incognito).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "Incognito mode is currently not available", Toast.LENGTH_SHORT).show();
                    }
                });

                sheetView.findViewById(R.id.socialmedia).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i =  new Intent(MainActivity.this,socialmedia.class);
                        startActivity(i);
                    }
                });

                sheetView.findViewById(R.id.scanQr).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i =  new Intent(MainActivity.this,ScanQrCode.class);
                        startActivity(i);
                    }
                });

                sheetView.findViewById(R.id.about).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MainActivity.this, about.class);
                        startActivity(intent);
                    }
                });

                sheetView.findViewById(R.id.settings).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MainActivity.this,BrowserSettings.class);
                        startActivity(intent);
                    }
                });

                sheetDialog.setContentView(sheetView);
                sheetDialog.show();
            }
        });

        btntvForTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "New Tab Will be available soon", Toast.LENGTH_SHORT).show();
            }
        });

        btnrefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edittexturl.selectAll();
                edittexturl.setText("");

                // use for open keyboard when all text are cleared from edit text using this button
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
                edittexturl.requestFocus();
            }
        });

        // Downloads

        webView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimeType, long contentLength) {

                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));

                request.setMimeType(mimeType);
                String cookies = CookieManager.getInstance().getCookie(url);
                request.addRequestHeader("cookies", cookies);

                request.addRequestHeader("User-Agent", userAgent);
                request.setDescription("Download File...");
                request.setTitle(URLUtil.guessFileName(url, contentDisposition, mimeType));
                request.allowScanningByMediaScanner();
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, URLUtil.guessFileName(url, contentDisposition, mimeType));
                DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                dm.enqueue(request);
                Toast.makeText(MainActivity.this, "Downloading File", Toast.LENGTH_SHORT).show();
            }
        });

        //Button Actions

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (webView.canGoBack()) {
                    webView.goBack();
                }
            }
        });

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (webView.canGoForward())
                    saveData();
                webView.goForward();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.stopLoading();
            }
        });

        refbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.reload();
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.loadUrl("https://google.com");
            }
        });



        btnbookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressedNew();
                Toast.makeText(MainActivity.this, "Page added to Bookmarks", Toast.LENGTH_SHORT).show();
            }
        });

        if (getIntent().getStringExtra("urls")!=null){
            webView.loadUrl(getIntent().getStringExtra("urls"));

        }
// Voice Search

        btnmic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speak();
            }
        });

        //------------------Open any website-----------------

        Uri uri = getIntent().getData();
        if (uri != null) {
            String path = uri.toString();
            webView.loadUrl(path);
        }

        //------------------end any website-----------------

    }

    private void onBackPressedNew() {
        Websites webv = new Websites(webView.getUrl());
        mydbHandlerBookmark.addUrl(webv);
        saveData();
    }

    private void saveData() {
    Websites web = new Websites (webView.getUrl());
    mydbHandler.addUrl(web);
    }



    private void speak() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hi! How Can I Help U...");
        try {
            startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT);
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    edittexturl.setText(result.get(0));
                    String urls = edittexturl.getText().toString();
                }
            }
        }
    }

    //-------------------------------Desktop site--------------------------------------------------//
    private void setDesktopSite(WebView webView, boolean enabled) {

        String newUserAgent = webView.getSettings().getUserAgentString();
        if (enabled) {
            try {
                String ua = webView.getSettings().getUserAgentString();
                String androidDosString = webView.getSettings().getUserAgentString().substring(ua.indexOf("("), ua.indexOf(")") + 1);
                newUserAgent = webView.getSettings().getUserAgentString().replace(androidDosString, "X11; Linux x86_64");
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            newUserAgent = null;
        }
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setDisplayZoomControls(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setUseWideViewPort(enabled);
        webView.getSettings().setLoadWithOverviewMode(enabled);
        webView.getSettings().setUserAgentString(newUserAgent);
        webView.reload();
    }
//--------------------------Exit Confirmation--------------------------
    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Are you sure you want to Exit ?");
            builder.setCancelable(false);

            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    MainActivity.super.onBackPressed();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }
}