package com.hasan.sonsuz;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;

import java.util.Random;

import sun.rmi.runtime.Log;


public class sema extends ApplicationAdapter {

    private final AdsController adsController;
    SpriteBatch batch;

    ShapeRenderer shapeRenderer;
    Texture Uzay;
    Texture dur;
    Texture basla;

    Texture bird;


    Texture bullet1;
    Texture bullet2;
    Texture bullet3;
    Texture bullet4;
    Texture bullet5;

    Texture point;
    Texture kalkan;
    Texture sesac;
    Texture seskes;
    Texture mermi;

    float width;
    float height;

    float birdX = 0;
    float birdY = 0;

    float pointx = 0;
    float pointy = 0;


    int gameState = 0;

    float birdVelocity = 0;
    float enemyVelocitiy = 0;
    float hızKontrol;

    int numberOfEnemies = 6;
    int speed = 0;
    int vek = 0;
    int kon = 1;

    int rekkon = 0;
    float[] enemyY;
    float mermiY = 0;

    float distance = 0;

    float[] enemyOffSet1;
    float[] enemyOffSet2;
    float[] enemyOffSet3;
    float[] enemyOffSet4;
    float[] enemyOffSet5;

    float bx;
    float by;

    float dx;
    float dy;

    float mx;
    float my;

    Random random;

    Circle birdCircle;
    Circle odur;
    Circle obasla;
    Circle bas;
    Circle poindaire;
    Circle mdaire;
    Circle mdur;


    Circle[] enemyCircle1;
    Circle[] enemyCircle2;
    Circle[] enemyCircle3;
    Circle[] enemyCircle4;
    Circle[] enemyCircle5;

    int score = 0;
    int scoredEnemy = 0;
    int sayac = 0;
    int sayac1 = 0;
    int enYuksek = 5;

    BitmapFont font;
    Preferences prefs;
    boolean tek = true;

    boolean durum = true;
    boolean ses = true;

    private Music music;
    private Music musicyandin;
    private Music musicpo;

    public sema(AdsController adsController) {
        this.adsController = adsController;
    }

    @Override
    public void create() {

        prefs = Gdx.app.getPreferences("ayar");
        enYuksek = prefs.getInteger("highscore", 0);
        batch = new SpriteBatch();

        shapeRenderer = new ShapeRenderer();
        // resimler

        Uzay = new Texture("back.png");
        bird = new Texture("roket.png");
        dur = new Texture("dur.png");
        basla = new Texture("basla.png");


        bullet1 = new Texture("top.png");
        bullet2 = new Texture("top.png");
        bullet3 = new Texture("top.png");
        bullet4 = new Texture("top.png");
        bullet5 = new Texture("top.png");

        point = new Texture("kalkan.png");
        kalkan = new Texture("mermi.png");
        sesac = new Texture("sesac.png");
        seskes = new Texture("seskes.png");

        mermi = new Texture("ates.png");
        font = new BitmapFont();

        font.setColor(Color.RED);

        font.getData().setScale(5);

        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();

        speed = (int) (height / 200);
        enemyVelocitiy = (height / 200);
        hızKontrol = enemyVelocitiy;


        birdX = (float) (width / 2.3);
        birdY = 0;
        random = new Random();

        bx = width / 8;
        by = (float) (height / 1.15);

        mx = (float) (width / 3.5);
        my = (float) (height / 1.15);

        dx = (float) (width / 3.4);
        dy = height / 3;

        pointy = height * 2;
        pointx = random.nextInt(Gdx.graphics.getWidth());

        distance = height / 2;

        enemyY = new float[numberOfEnemies];


        enemyOffSet1 = new float[numberOfEnemies];
        enemyOffSet2 = new float[numberOfEnemies];
        enemyOffSet3 = new float[numberOfEnemies];
        enemyOffSet4 = new float[numberOfEnemies];
        enemyOffSet5 = new float[numberOfEnemies];

        birdCircle = new Circle();
        poindaire = new Circle();
        odur = new Circle();
        obasla = new Circle();
        bas = new Circle();
        mdaire = new Circle();
        mdur = new Circle();

        enemyCircle1 = new Circle[numberOfEnemies];
        enemyCircle2 = new Circle[numberOfEnemies];
        enemyCircle3 = new Circle[numberOfEnemies];
        enemyCircle4 = new Circle[numberOfEnemies];
        enemyCircle5 = new Circle[numberOfEnemies];

        music = Gdx.audio.newMusic(Gdx.files.internal("m.mp3"));
        music.setLooping(true);
        music.setVolume(0.1f);
        music.play();
        musicyandin = Gdx.audio.newMusic(Gdx.files.internal("yandin.mp3"));
        musicyandin.setVolume(0.1f);
        musicpo = Gdx.audio.newMusic(Gdx.files.internal("point.mp3"));
        musicpo.setVolume(0.2f);
        //shapeRenderer = new ShapeRenderer();

        for (int i = 0; i < numberOfEnemies; i++) {
            // KurÅŸunlarÄ±n x ekseninde belli aralÄ±klarla gelmesini ayarlÄ±yoruz.
            enemyY[i] = height + i * distance;

            // KurÅŸunlarÄ±n y ekseninde rastgele gelmesini ayarlÄ±yoruz.
            enemyOffSet1[i] = random.nextInt(Gdx.graphics.getWidth());
            enemyOffSet2[i] = random.nextInt(Gdx.graphics.getWidth());
            enemyOffSet3[i] = random.nextInt(Gdx.graphics.getWidth());
            enemyOffSet4[i] = random.nextInt(Gdx.graphics.getWidth());
            enemyOffSet5[i] = random.nextInt(Gdx.graphics.getWidth());

            // GÃ¶rÃ¼nmez dairelerimizi ayarlÄ±yoruz.
            enemyCircle1[i] = new Circle();
            enemyCircle2[i] = new Circle();
            enemyCircle3[i] = new Circle();
            enemyCircle4[i] = new Circle();
            enemyCircle5[i] = new Circle();

        }

    }


    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        sayac++;
        batch.begin();


        batch.draw(Uzay, 0, 0, width, height);
        batch.draw(bird, birdX, birdY, width / 6, height / 6);


        enYuksek = prefs.getInteger("highscore", 0);
        if (score > enYuksek) {
            prefs.putInteger("highscore", score);
            prefs.flush();
        }

        if (sayac % 677 == 0) {     //ortalama her 11 sn de taÅŸlarÄ±n hÄ±zÄ± artÄ±cak
            if (enemyVelocitiy < 35)
                enemyVelocitiy += 2;
            speed++;

        }



        batch.draw(mermi, birdX+width/20, birdY + mermiY, width / 25, height / 50);
        if (mermiY< height / 55) {

            mermiY = height + numberOfEnemies * distance;

        } else {

            mermiY = mermiY + 10;

        }
        if (vek == 1 && durum) {

            sayac1++;
            hızKontrol = 40;
            batch.draw(kalkan, birdX, birdY, width / 6, height / 5);
            if (sayac1 >= 300) {     //ortalama her 11 sn de taÅŸlarÄ±n hÄ±zÄ± artÄ±cak
                vek = 0;
                sayac1 = 0;

            } else if (sayac1 >= 240) {
                hızKontrol = 0;
                kon = 1;
                pointy = height * 4;
            } else {
                if (birdX < width / 2) {
                    kon = 0;
                    birdX += 10;
                }
                if (birdX > width / 2) {
                    kon = 0;
                    birdX -= 10;
                }

            }
        }
        if (vek == 0) {
            sayac1 = 0;
            hızKontrol = 0;
            batch.draw(point, pointx, pointy, width / 8, height / 12);

        }


        if (gameState == 1) {

            if (birdY < height / 5) {
                birdY += 10;
            }
            // taÅŸlar y ekseninde gemiyi geÃ§tiÄŸi zaman..
            if (enemyY[scoredEnemy] < birdY) {
                score++;

                // scoredEnemy 0 ile 2 arasÄ±nda tutuyoruz.
                if (scoredEnemy < numberOfEnemies - 1) {
                    scoredEnemy++;
                } else {
                    scoredEnemy = 0;
                }
            }

            //kontroller
            if (Gdx.input.getX() < width / 2) {
                if (birdY >= height / 5) {
                    if (kon == 1)
                        birdX -= speed;
                }

            }

            if (Gdx.input.getX() > width / 2) {
                if (birdY >= height / 5) {
                    if (kon == 1)
                        birdX += speed;
                }
            }


            if (pointy < 0) {
                pointx = random.nextInt((int) (Gdx.graphics.getWidth() - width / 8));
                pointy = height * 4;

            }

            for (int i = 0; i < numberOfEnemies; i++) {
                // KurÅŸunlar ekranÄ±n sonuna geldiÄŸi zaman tekrarda baÅŸlatÄ±yoruz.
                if (enemyY[i] < height / 22) {
                    // KurÅŸunlarÄ±n y eksenindeki uzaklÄ±ÄŸÄ±nÄ± ayarlÄ±yoruz.
                    enemyY[i] = height + numberOfEnemies * distance;
                    mermiY = height + numberOfEnemies * distance;

                    // KurÅŸunlarÄ±n x ekseninden uzaklÄ±klarÄ±nÄ± tekrardan ayarlÄ±yoruz.
                    enemyOffSet1[i] = random.nextInt(Gdx.graphics.getWidth());
                    enemyOffSet2[i] = random.nextInt(Gdx.graphics.getWidth());
                    enemyOffSet3[i] = random.nextInt(Gdx.graphics.getWidth());
                    enemyOffSet4[i] = random.nextInt(Gdx.graphics.getWidth());
                    enemyOffSet5[i] = random.nextInt(Gdx.graphics.getWidth());
                } else {
                    // EÄŸer ekranÄ±n sonuna gelinmediyse kurÅŸunlarÄ±n sÃ¼rekli hareket etmesi iÃ§in
                    // KurÅŸunun hÄ±zÄ±nÄ± y ekseninden Ã§Ä±kartÄ±yoruz.
                    enemyY[i] = enemyY[i] - enemyVelocitiy - hızKontrol;
                    mermiY = mermiY + 10;

                }

                // KurÅŸunlarÄ± Ã§izdiriyoruz.
                batch.draw(bullet1, enemyOffSet1[i], enemyY[i], width / 12, height / 18);
                batch.draw(bullet2, enemyOffSet2[i], enemyY[i], width / 12, height / 18);
                batch.draw(bullet3, enemyOffSet3[i], enemyY[i], width / 12, height / 18);
                batch.draw(bullet4, enemyOffSet4[i], enemyY[i], width / 12, height / 18);
                batch.draw(bullet5, enemyOffSet5[i], enemyY[i], width / 12, height / 18);

                // GÃ¶rÃ¼nmez dairelerimizi Ã§izdiriyoruz.
                enemyCircle1[i] = new Circle(enemyOffSet1[i] + width / 26, enemyY[i] + height / 34, width / 25);
                enemyCircle2[i] = new Circle(enemyOffSet2[i] + width / 26, enemyY[i] + height / 34, width / 25);
                enemyCircle3[i] = new Circle(enemyOffSet3[i] + width / 26, enemyY[i] + height / 34, width / 25);
                enemyCircle4[i] = new Circle(enemyOffSet4[i] + width / 26, enemyY[i] + height / 34, width / 25);
                enemyCircle5[i] = new Circle(enemyOffSet5[i] + width / 26, enemyY[i] + height / 34, width / 25);
            }

            pointy -= enemyVelocitiy;
            //pointer çizzildi


            if (birdX < 0) {
                birdX = width - width / 9;
            } else if (birdX > width - width / 9) {
                birdX = 0;
            }


        } else if (gameState == 0) {
            if (Gdx.input.justTouched()) {
                gameState = 1;
                enemyVelocitiy = 10;
                speed = 7;
            }
        } else if (gameState == 2) {
            // Game Over ve Tap to Play Again! ekrana yazdÄ±rÄ±yoruz.
            if (tek == true) {
                Gdx.input.vibrate(200);
                musicyandin.play();
                tek = false;
            }


            font.draw(batch, "Bittii !", (float) (width / 2.3), height / 2);
            font.draw(batch, "Ekrana dokun", (float) (width / 3.0), height / 3);
            pointy = height * 4;
            if (rekkon % 5 == 0) {
                adsController.showInterstitialAd();
            }

            // Oyun bittikten sonra ekrana tÄ±klanÄ±rsa..
            if (Gdx.input.justTouched()) {
                // Scoru ve kuÅŸun hÄ±zÄ±nÄ± sÄ±fÄ±rlÄ±yoruz.
                rekkon++;
                score = 0;
                speed = 4;
                birdVelocity = 0;
                enemyVelocitiy = 10;
                tek = true;


                gameState = 1;

                // KuÅŸu baÅŸlangÄ±Ã§taki haline getiriyoruz
                birdX = width / 2;
                birdY = 0;

                // taslari ilk haline getiriyoruz.
                for (int i = 0; i < numberOfEnemies; i++) {
                    enemyY[i] = height + i * distance;

                    enemyOffSet1[i] = random.nextInt(Gdx.graphics.getWidth());
                    enemyOffSet2[i] = random.nextInt(Gdx.graphics.getWidth());
                    enemyOffSet3[i] = random.nextInt(Gdx.graphics.getWidth());
                    enemyOffSet4[i] = random.nextInt(Gdx.graphics.getWidth());
                    enemyOffSet5[i] = random.nextInt(Gdx.graphics.getWidth());

                    enemyCircle1[i] = new Circle();
                    enemyCircle2[i] = new Circle();
                    enemyCircle3[i] = new Circle();
                    enemyCircle4[i] = new Circle();
                    enemyCircle5[i] = new Circle();
                }
            }

            scoredEnemy = 0;

        }


        // çizimler


        font.draw(batch, String.valueOf(score), width / 2, height / 2 + height / 3);
        font.draw(batch, String.valueOf(enYuksek), (float) (width / 1.2), (float) (height / 1.1));


        //daireler


        birdCircle.set(birdX + width / 13, birdY + height / 10, width / 19);
        poindaire.set(pointx + width / 16, pointy + height / 24, width / 16);
        bas.set(Gdx.input.getX(), height - Gdx.input.getY(), width / 20);


        if (durum) {
            batch.draw(dur, bx, by, width / 9, height / 15);
            odur.set(bx + width / 18, by + height / 30, width / 13);
            enemyVelocitiy = 10;
            hızKontrol = 0;
            speed = 7;
        }
        if (!durum) {
            enemyVelocitiy = 0;
            hızKontrol = 0;
            speed = 0;
            sayac = 0;
            batch.draw(basla, dx, dy, (float) (width / 2.7), height / 5);
            obasla.set(dx + width / 5, dy + height / 10, width / 5);
        }
        if (ses) {
            music.play();
            batch.draw(sesac, mx, my, width / 9, height / 15);
            mdur.set(mx + width / 20, my + height / 30, width / 13);
            mdaire.set(0, 0, 0);

        }
        if (!ses) {
            music.pause();
            batch.draw(seskes, mx + width / 5, my, width / 9, height / 15);
            mdaire.set(mx + width / 5 + width / 20, my + height / 30, width / 13);
            mdur.set(0, 0, 0);

        }

       /* shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.circle(bas.x,bas.y, bas.radius);
        //daireleri görünür yap
       /* shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.circle(birdCircle.x, birdCircle.y, birdCircle.radius);
        shapeRenderer.circle(obasla.x, obasla.y, obasla.radius);
        shapeRenderer.circle(odur.x, odur.y, odur.radius);
*/
        // durum kontrolleri
        {
            if (vek == 0) {
                for (int i = 0; i < numberOfEnemies; i++) {
                    /*
                    shapeRenderer.circle(enemyCircle1[i].x, enemyCircle1[i].y, enemyCircle1[i].radius);
                    shapeRenderer.circle(enemyCircle2[i].x, enemyCircle2[i].y, enemyCircle2[i].radius);
                    shapeRenderer.circle(enemyCircle3[i].x, enemyCircle3[i].y, enemyCircle3[i].radius);
                    shapeRenderer.circle(enemyCircle4[i].x, enemyCircle4[i].y, enemyCircle4[i].radius);
                    shapeRenderer.circle(enemyCircle5[i].x, enemyCircle5[i].y, enemyCircle5[i].radius);

                     */
                    if (Intersector.overlaps(birdCircle, enemyCircle1[i]) ||
                            Intersector.overlaps(birdCircle, enemyCircle2[i]) ||
                            Intersector.overlaps(birdCircle, enemyCircle3[i]) ||
                            Intersector.overlaps(birdCircle, enemyCircle4[i]) ||
                            Intersector.overlaps(birdCircle, enemyCircle5[i])) {
                        gameState = 2;

                    }
                }
            }


            if (Intersector.overlaps(birdCircle, poindaire)) {
                vek = 1;
                musicpo.play();
            }

            if (Intersector.overlaps(odur, bas)) {///durdur butonu
                durum = false;

            }

            if (Intersector.overlaps(obasla, bas)) {
                durum = true;
            }
            if (Intersector.overlaps(mdaire, bas)) {///durdur butonu
                ses = true;


            }
            if (Intersector.overlaps(mdur, bas)) {
                ses = false;

            }


        }

        //  shapeRenderer.end();
        batch.end();
    }

    @Override
    public void dispose() {
        bird.dispose();

    }


}