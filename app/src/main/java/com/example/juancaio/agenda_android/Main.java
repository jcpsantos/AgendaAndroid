package com.example.juancaio.agenda_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class Main extends AppCompatActivity {


    Contato contatoEditado = null;

    private int getIndex(Spinner spinner, String myString)
    {
        int index = 0;
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //verifica se começou agora ou se veio de uma edição
        Intent intent = getIntent();
        if(intent.hasExtra("contato")){
            findViewById(R.id.includemain).setVisibility(View.INVISIBLE);
            findViewById(R.id.includecadastro).setVisibility(View.VISIBLE);
            findViewById(R.id.fab).setVisibility(View.INVISIBLE);
            contatoEditado = (Contato) intent.getSerializableExtra("contato");
            EditText txtNome = (EditText)findViewById(R.id.txtNome);
            EditText txtTelefone = findViewById(R.id.txtTelefone);
            EditText txtCelular = findViewById(R.id.txtCelular);
            EditText txtEmail = findViewById(R.id.txtEmail);

            txtNome.setText(contatoEditado.getNome());
            txtTelefone.setText(contatoEditado.getTelefone());
            txtCelular.setText(contatoEditado.getCelular());
            txtEmail.setText(contatoEditado.getEmail());
            if(contatoEditado.getN_agenda() != null){
                RadioButton rb;
                if (contatoEditado.getN_agenda().equals("Agenda 1"))
                    rb = findViewById(R.id.agenda1);
                else
                    rb = findViewById(R.id.agenda2);
                rb.setChecked(true);
            }
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.includemain).setVisibility(View.INVISIBLE);
                findViewById(R.id.includecadastro).setVisibility(View.VISIBLE);
                findViewById(R.id.fab).setVisibility(View.INVISIBLE);
            }
        });

        Button btnCancelar = (Button) findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.includemain).setVisibility(View.VISIBLE);
                findViewById(R.id.includecadastro).setVisibility(View.INVISIBLE);
                findViewById(R.id.fab).setVisibility(View.VISIBLE);

                EditText txtNome = findViewById(R.id.txtNome);
                EditText txtTelefone = findViewById(R.id.txtTelefone);
                EditText txtCelular = findViewById(R.id.txtCelular);
                EditText txtEmail = findViewById(R.id.txtEmail);
                RadioGroup rgAgenda = findViewById(R.id.agenda);

                txtNome.setText("");
                txtTelefone.setText("");
                txtCelular.setText("");
                txtEmail.setText("");
                rgAgenda.setSelected(false);

            }

        });



        Button btnSalvar = (Button) findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //carregando os campos
                EditText txtNome = findViewById(R.id.txtNome);
                RadioGroup rgAgenda = findViewById(R.id.agenda);
                EditText txtTelefone = findViewById(R.id.txtTelefone);
                EditText txtCelular = findViewById(R.id.txtCelular);
                EditText txtEmail = findViewById(R.id.txtEmail);

                //pegando os valores
                String nome = txtNome.getText().toString();
                String telefone = txtTelefone.getText().toString();
                String celular = txtCelular.getText().toString();
                String email = txtEmail.getText().toString();
                String agenda = rgAgenda.getCheckedRadioButtonId() == R.id.agenda1 ? "Agenda 1" : "Agenda 2";


                if (nome.equals("") || telefone.equals("") || celular.equals("") || email.equals("")){
                    Snackbar.make(view, "Ops...=( Dados em branco, por favor complete todos os dados do contato!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }else {
                    //salvando os dados
                    ContatoDAO dao = new ContatoDAO(getBaseContext());
                    boolean sucesso;
                    if(contatoEditado != null)
                        sucesso = dao.salvar(contatoEditado.getId(), agenda , nome, telefone, celular, email);
                    else
                        sucesso = dao.salvar(agenda, nome, telefone, celular, email);

                    if(sucesso) {
                        Contato contato = dao.retornarUltimo();
                        if(contatoEditado != null){
                            adapter.atualizarContato(contato);
                            contatoEditado = null;
                        }else
                            adapter.adicionarContato(contato);

                        //limpa os campos
                        contatoEditado = null;
                        txtNome.setText("");
                        txtTelefone.setText("");
                        txtCelular.setText("");
                        txtEmail.setText("");
                        rgAgenda.setSelected(false);

                        Snackbar.make(view, "Oba!!! ;) Salvou!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                        findViewById(R.id.includemain).setVisibility(View.VISIBLE);
                        findViewById(R.id.includecadastro).setVisibility(View.INVISIBLE);
                        findViewById(R.id.fab).setVisibility(View.VISIBLE);
                    } else {
                        Snackbar.make(view, "Ops...=( Erro ao salvar, consulte os logs!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    }

                }

            }

        });

        configurarRecycler();
    }

    RecyclerView recyclerView;
    ContatoAdapter adapter;


    private void configurarRecycler() {
        // Configurando o gerenciador de layout para ser uma lista.
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        // Adiciona o adapter que irá anexar os objetos à lista.
        ContatoDAO dao = new ContatoDAO(this);
        adapter = new ContatoAdapter(dao.retornaTodos());
        recyclerView.setAdapter(adapter);
        // Configurando um separador entre linhas, para uma melhor visualização.
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
