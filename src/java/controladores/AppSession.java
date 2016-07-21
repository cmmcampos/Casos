package controladores;

import entidades.Usuarios;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;



@ManagedBean
@SessionScoped
public class AppSession implements Serializable {
	
         private Usuarios usuarioLogeado;

	public Usuarios getUsuario() {
		return usuarioLogeado;
	}

	public void setUsuario(Usuarios usuario) {
		this.usuarioLogeado = usuario;
	}

}
