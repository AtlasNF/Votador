package br.com.atlasnf.util;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import br.com.atlasnf.model.Recurso;

public class RecursoDataModel extends ListDataModel<Recurso> implements SelectableDataModel<Recurso> {


	private List<Recurso> recursos;
	
	
	public RecursoDataModel(List<Recurso> data) {
		this.recursos = data;
	}
	
	@Override
	public Recurso getRowData(String id) {
		for(Recurso recurso : recursos) {
			if(recurso.getId().equals(id));
			return recurso;
		}
		return null;
	}

	@Override
	public Object getRowKey(Recurso recurso) {
		return recurso.getId();
	}


	
	

}
