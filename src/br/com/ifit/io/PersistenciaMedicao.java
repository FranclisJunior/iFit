package br.com.ifit.io;

import java.util.List;

import br.com.ifit.model.Medicao;

public interface PersistenciaMedicao {
	
	public String saveMedicao(Medicao medicao);
	public Medicao getMedicao(int id);
	public List<Medicao> getMedicoes(String cpfAluno);
	public String updateMedicao(Medicao medicao);
	public String deleteMedicao(int id);
	
}
