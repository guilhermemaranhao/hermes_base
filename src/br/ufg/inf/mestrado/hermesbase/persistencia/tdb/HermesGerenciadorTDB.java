package br.ufg.inf.mestrado.hermesbase.persistencia.tdb;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.query.ReadWrite;
import com.hp.hpl.jena.tdb.TDBFactory;

/**
 * Gerenciador de TDB não acessado por Hermes até o momento.
 * @author guilhermemaranhao
 *
 */
public class HermesGerenciadorTDB {
	
	Dataset dataset = null;

	public HermesGerenciadorTDB()
	{
		
	}
	
	public void conectar()
	{
		String diretorio = "HermesDatabases/Dataset1";
		dataset = TDBFactory.createDataset(diretorio);
	}
	
	public void atualizar(OntModel model)
	{
		dataset.begin(ReadWrite.WRITE);
		dataset.setDefaultModel(model);
		dataset.commit();
		dataset.end();
	}
	
	public void consultar(String query)
	{
		dataset.begin(ReadWrite.READ);
		dataset.getDefaultModel();
		dataset.end();
	}
	
	public void fecharConexao()
	{
		dataset.close();
	}
}
