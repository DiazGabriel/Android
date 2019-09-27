package com.example.calculatrice

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var showCalcul: TextView
    private lateinit var button0: Button
    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button
    private lateinit var button5: Button
    private lateinit var button6: Button
    private lateinit var button7: Button
    private lateinit var button8: Button
    private lateinit var button9: Button
    private lateinit var buttonSupprimer: Button
    private lateinit var buttonEgal: Button
    private lateinit var buttonPlus: Button
    private lateinit var buttonMoins: Button
    private lateinit var buttonMultiplication: Button
    private lateinit var buttonDivision: Button
    private var champCalcul: String = ""
    private var resultat1: Int = 0
    private var resultat2: Int = 0
    private lateinit  var codeResultat: String
    private var total: Int = 0
    private var signe: String = ""
    private var codeEgal: String = "NON"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // On récupère les éléments graphiques pour pouvoir les manipuler
        this.showCalcul = findViewById(R.id.showCalcul)
        this.button0 = findViewById(R.id.button0)
        this.button1 = findViewById(R.id.button1)
        this.button2 = findViewById(R.id.button2)
        this.button3 = findViewById(R.id.button3)
        this.button4 = findViewById(R.id.button4)
        this.button5 = findViewById(R.id.button5)
        this.button6 = findViewById(R.id.button6)
        this.button7 = findViewById(R.id.button7)
        this.button8 = findViewById(R.id.button8)
        this.button9 = findViewById(R.id.button9)
        this.buttonSupprimer = findViewById(R.id.buttonVirgul)
        this.buttonEgal = findViewById(R.id.buttonEgal)
        this.buttonPlus = findViewById(R.id.buttonPlus)
        this.buttonMoins = findViewById(R.id.buttonMoins)
        this.buttonMultiplication = findViewById(R.id.buttonMultiplication)
        this.buttonDivision = findViewById(R.id.buttonDivision)

        //Puis on initilise la valeur du champ de texte showCalcul. Permettra d'afficher le calcul à l'écran

        champCalcul = ""
        codeResultat = "1"
        signe = ""
        showCalcul.text = champCalcul.toString()

        //Mise en place des actionListner sur les boutons

        button0.setOnClickListener { traitementCalcul(0) }

        button1.setOnClickListener { traitementCalcul(1) }

        button2.setOnClickListener { traitementCalcul(2) }

        button3.setOnClickListener { traitementCalcul(3) }

        button4.setOnClickListener { traitementCalcul(4) }

        button5.setOnClickListener { traitementCalcul(5) }

        button6.setOnClickListener { traitementCalcul(6) }

        button7.setOnClickListener { traitementCalcul(7) }

        button8.setOnClickListener { traitementCalcul(8) }

        button9.setOnClickListener { traitementCalcul(9) }

        buttonSupprimer.setOnClickListener {
            // On supprime tout
            signe = ""
            resultat1 = 0
            resultat2 = 0

            resultat()
        }

        buttonPlus.setOnClickListener { traitementSigne("+") }

        buttonMoins.setOnClickListener { traitementSigne("-") }

        buttonMultiplication.setOnClickListener { traitementSigne("*") }

        buttonDivision.setOnClickListener { traitementSigne("/") }

        buttonEgal.setOnClickListener {
            if (signe === "+") {
                resultat1 = resultat1 + resultat2
            } else if (signe === "-") {
                resultat1 = resultat1 - resultat2
            } else if (signe === "*") {
                resultat1 = resultat1 * resultat2
            } else if (signe === "/") {
                resultat1 = resultat1 / resultat2
            }

            codeEgal = "OUI"
            signe = ""
            resultat2 = 0

            resultat()
        }

    }

    fun traitementCalcul(nombre: Int) {

        // Si la variable chiffre est vide c'est que l'on est en train de rensigner la
        // première composante de l'opération.
        // Sinon c'est qu'on remplie la seconde

        if (codeEgal === "OUI") {
            codeEgal = "NON"
            resultat1 = 0
            resultat2 = 0
            signe = ""
        }

        if (signe == "") {

            //Si le chiffre est inferrieur à dix c'est que c'est le premier que l'on rempli.
            // sinon on multiplie le resultat par 10 avant d'additionner

            if (resultat1 == 0) {
                resultat1 = nombre
            } else {
                resultat1 = resultat1 * 10 + nombre
            }
        } else {
            if (resultat2 == 0) {
                resultat2 = nombre
            } else {
                resultat2 = resultat2 * 10 + nombre
            }
        }

        resultat()

    }

    fun traitementSigne(operateur: String) {

        //Le code résultat permet de savoir si on remplie le prmier ou le deuxième champ.
        //si il est à 1, on le passe à 2 et on initialise le second champ.
        //Si il est à 2 on effectue l'opération et on continu

        if (codeEgal === "OUI") { codeEgal = "NON" }

        if (codeResultat === "1") {

            codeResultat = "2"
            signe = operateur

        } else {

            if (signe === "+") {
                resultat1 = resultat1 + resultat2
            } else if (signe === "-") {
                resultat1 = resultat1 - resultat2
            } else if (signe === "*") {
                resultat1 = resultat1 * resultat2
            } else if (signe === "/") {
                resultat1 = resultat1 / resultat2
            }

            signe = operateur
            resultat2 = 0
        }

        resultat()

    }

    fun resultat() {

        // On affiche la saisie

        if (resultat2 != 0) {
            champCalcul = resultat1.toString() + signe + resultat2.toString()
        } else {
            champCalcul = resultat1.toString() + signe
        }
        showCalcul.text = champCalcul

    }

}
