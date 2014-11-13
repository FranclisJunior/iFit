package br.com.ifit.io.dao;

import java.util.List;

import br.com.ifit.io.ConnectionFactory;
import br.com.ifit.io.PersistenciaMedicao;
import br.com.ifit.model.Medicao;

public class DAOMedicao extends ConnectionFactory implements PersistenciaMedicao {

	@Override
	public String saveMedicao(Medicao medicao) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Medicao getMedicao(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Medicao> getMedicoes(String cpfAluno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateMedicao(Medicao medicao) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteMedicao(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
