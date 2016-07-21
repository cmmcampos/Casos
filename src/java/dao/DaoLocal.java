/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import controladores.AppSession;
import entidades.Roles;
import entidades.Servicios;
import entidades.Usuarios;
import entidades.Estados;
import entidades.Dependencias;
import entidades.DireccionesNacionales;
import entidades.EncaRequerimientos;
import entidades.CategoriaServicios;
import entidades.Documentos;
import entidades.Tecnicos;
import entidades.ViaSolicitudes;
import entidades.DetaRequerimientos;
import entidades.UnidadesEjecutoras;
import entidades.TiposUsuarios;
import entidades.DireccionesInter;
import entidades.AsignarTecnico;
import java.io.InputStream;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @
 */

@Local
public interface DaoLocal {

    public AsignarTecnico traeAsignacionDelRequeri(EncaRequerimientos enca, int i);

    public void borrarDocu(List<Documentos> docum);

    public boolean insertDocumento(Documentos d);

    public List<DireccionesInter> traeDirecInterDeIdNacio(int i);
    
    public List<Dependencias> traeDepenDeIdDInter(Integer i);

    public TiposUsuarios traeTipoUsuario(int i);

    public List<Estados> traeEstados();

    public TiposUsuarios traeTipoUsuario1(int i);

    public Dependencias traeDependencia2(int i);

    public boolean updateDocumento(Documentos docActuali);

    public UnidadesEjecutoras traeUnidadEjecutora(int i);

    public List<UnidadesEjecutoras> traeUnidadesEje();

    public int traeUltimoIdTecnico();

    public Roles traeRol(int i);

    public List<Documentos> traeDocumentos();

    public boolean insertCategoriaServicio(CategoriaServicios cateServ);

    public boolean updateCategoriaServicio(CategoriaServicios cateServ);

    public List<CategoriaServicios> traeCategoriaServicios(AppSession appSession);

    /**
     *
     * @hecho para controladorServicios
     */
    public boolean insertServicios(Servicios serv);

    public List<Servicios> traeServicios(AppSession appSession);

    public boolean updateServicio(Servicios serv);

    public boolean insertDependencias(Dependencias dep);

    public boolean updateDependencias(Dependencias dep);

    public List<Dependencias> traeDependencias();
    
    public List<Tecnicos> traeTecnic(AppSession appSession);

    public boolean insertDetaRequerimiento(DetaRequerimientos detaReq);

    public boolean updateDetaRequerimiento(DetaRequerimientos detaRe);

    public List<DetaRequerimientos> traeDetaRequerimientos();

    public List<DetaRequerimientos> traeDetaRequerimientosDeUnEncaReque(Integer i);

    public boolean insertDireccionInter(DireccionesInter direInter);

    public boolean updateDireccionInter(DireccionesInter direcInter);

    public List<DireccionesInter> traeDireccionesInter();

    public int traeUltimoIdDireNac();

    public boolean insertDireccionesNacionales(DireccionesNacionales direcNacionales);

    public boolean updateDireccionesNacionales(DireccionesNacionales direcNacionales);

    public List<DireccionesNacionales> traeDireccionesNacionales();

    public void updateEncaRequerimiento(EncaRequerimientos encaR, List<Documentos> listaD);

    public boolean insertEncaRequerimientos(EncaRequerimientos encaR, List<Documentos> listaD, int idUni);
    
    public Integer encuentraCorrel(int i);
    
    public Integer actualizaCorrel(Integer i, Integer nuevoCorrel);

    public void eliminarEncaRequerimiento(int id);

    public int traeId(List<EncaRequerimientos> listEnca);

    public int devuId(List<Documentos> listDoc);

    public void TransFerFile(String fileName, InputStream in, String detino, int id, int tamano);

    public boolean updateEncaRequerimientos(EncaRequerimientos encaR);

    public void eliminarAsinaTecnico(int i);

    public List<EncaRequerimientos> traeRequrimientosDelTecnico(Integer idTecnico, Integer idUnidadEj);
    
    public List<EncaRequerimientos> traeTodosRequeDelTecnico(Integer idTecnico, Integer idUnidadEj);

    public List<EncaRequerimientos> traeEncaRequerimientoAModificar(int idU);

    public List<EncaRequerimientos> traeEncaRequrimientos(int i, int j, int h);
    
    public List<EncaRequerimientos> traeRequeParaVerificar (int iEst, int iUnd);

    public int traeUltimoIdEstados();

    public boolean insertEstados(Estados estado);

    public boolean updateEstados(Estados estado);

    public int traeUltimoId();

    public boolean insertRoles(Roles rol);

    public boolean updateRoles(Roles rol);

    public boolean insertServicio(Servicios servi);

    public boolean updateServicios(Servicios servi);

    public List<Servicios> traeServicios(int i);

    public boolean insertTecnico(Tecnicos tec);

    public boolean updateTecnico(Tecnicos tecni);

    public List<Tecnicos> traeTecnicos(int idUni);

    public Tecnicos traeTecnico(int i);

    public Tecnicos traeTecnico(String usua);

    public int existeTecnico(String usua);

    public int traeUltimoIdTipoUsuario();

    public boolean insertTipoUsuario(TiposUsuarios tipoUsu);

    public boolean updateTipoUsuario(TiposUsuarios tipoUsu);

    public int traeIltimoIdDeUnidadEje();

    public boolean insertUnidadEjecutora(UnidadesEjecutoras unidadEjecu);

    public boolean updateUnidadEjecutora(UnidadesEjecutoras unidadEje);

    public int traeUltimoIdUsuario();

    public boolean insertUsuario(Usuarios usua);

    public void operacionTecnicoYUsuario(Usuarios u, Tecnicos t);

    public boolean updateUsuario(Usuarios usuario);
    
    public List<Usuarios> traeTecnicosActivos(int idu);

    public List<Usuarios> traeUsuariosTecnicos(int i);

    public List<Usuarios> traeUsuariosActivosDeUnidadEjecutora(int i);

    public List<Usuarios> traeUsuarios(int i);

    public boolean insertViaSolicitudes(ViaSolicitudes viaSolicitudes);

    public boolean updateViaSolicitudes(ViaSolicitudes viaSolicitudes);

    public List<ViaSolicitudes> traeViaSolicitudes();

    public boolean insertAsignarTecnico(AsignarTecnico asigTecni);
    
    public Integer insertAsignarTecnico2(Integer idEnc, Integer idTec, Integer idUnE, Integer tecActual);

    public AsignarTecnico encontrarAsignaTecnico(int idRequeri, int idTec, int idUniEje);
    
    
    public boolean updateAsignarTecnico(AsignarTecnico asigTecni);

    public Integer encontrarNumCaso(int i);

    public Integer encontrarNumCaso2(int i);

    public Integer encontrarTecnico(Integer i);

    public String traeDependencia(int idUsus);

    public List<Object[]> traeListaDeRequeriYAsigna(int idUni);

    public List<SumaPesoTecnico> traeTecnicosConSuPeso(int valor);

    public Tecnicos devuelveTecnicos(Integer i);

    public AsignarTecnico traeAsignarTecnicoEncaRequeri(Integer i, String usuarioLogeado);

    public int traeUltimoDocumento();

    public boolean traeEstadoPorElTecnico(int idEnca, int idTec, int idUnidadEje);

    public List<TiposUsuarios> traeTipoUsuarios();

    public Usuarios traeUsuarioLogeado(String nombre);

    public List<Roles> traeRoles();

    public List<Estados> listaEstados();

    public Integer actualizaTecR(Integer idTec2);    

    public Tecnicos tecnicoPorId (int idTecnico);

    public List<CategoriaServicios> traeCategoriaServicios( int i);
   
}