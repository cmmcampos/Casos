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
import entidades.RequeVerificados;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @
 */
@Stateless
public class Dao implements DaoLocal {

    @PersistenceContext(unitName = "PU")
    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
    //metodos para abrir y cerrar la conexion

    //metodos de dml y ddl
    @Override
    public AsignarTecnico traeAsignacionDelRequeri(EncaRequerimientos enca, int i) {
        AsignarTecnico asignacion = new AsignarTecnico();
        List<Object[]> asi = new ArrayList<Object[]>();
        List<AsignarTecnico> listAsig = new ArrayList<AsignarTecnico>();
        List<EncaRequerimientos> listaEn = new ArrayList<EncaRequerimientos>();
        try {

            Query q = this.em.createNamedQuery("EncaRequerimientos.findBytodo");
            q.setParameter("idu", i);
            asi = q.getResultList();
            for (Object[] fila : asi) {
                listaEn.add((EncaRequerimientos) fila[0]);
                listAsig.add((AsignarTecnico) fila[1]);
            }
            asignacion = listAsig.get(listaEn.indexOf(enca));

            return asignacion;
        } catch (Exception e) {

            return asignacion;
        }
    }

    @Override
    public void borrarDocu(List<Documentos> docum) {
        try {


            for (Documentos d : docum) {
                Query query = em.createQuery("DELETE FROM Documentos d WHERE d.idDocumento=" + d.getIdDocumento());
                query.executeUpdate();
            }


        } catch (Exception e) {
        }
    }

    @Override
    public boolean insertDocumento(Documentos d) {
        try {
           em.persist(d);

            return true;
        } catch (Exception e) {


            System.out.println(e.toString());
            return false;
        }
    }

    @Override
    public List<DireccionesInter> traeDirecInterDeIdNacio(int i) {
        List<DireccionesInter> lista = new ArrayList<DireccionesInter>();
        try {

            Query q = this.em.createNamedQuery("DireccionesInter.findIdNacional");
            q.setParameter("idDire", i);
            lista = (List<DireccionesInter>) q.getResultList();

            return lista;
        } catch (Exception e) {

            return lista;
        }
    }

    @Override
    public List<Dependencias> traeDepenDeIdDInter(Integer i) {
        List<Dependencias> lista = new ArrayList<Dependencias>();
        try {

            Query q = this.em.createNamedQuery("Dependencias.findIdDInter");
            q.setParameter("idDir", i);
            lista = (List<Dependencias>) q.getResultList();

            return lista;
        } catch (Exception e) {

            return lista;
        }
    }
    
    
    @Override
    public TiposUsuarios traeTipoUsuario(int i) {
        TiposUsuarios t = new TiposUsuarios();
        try {

            Query q = this.em.createNamedQuery("TiposUsuarios.findByIdTipoUsuario");
            q.setParameter("idTipoUsuario", i);
            t = (TiposUsuarios) q.getSingleResult();

            return t;
        } catch (Exception e) {

            return t;
        }
    }

    @Override
    public List<Estados> traeEstados() {
        List<Estados> lista = new ArrayList<Estados>();
        try {

            Query q = this.em.createNamedQuery("Estados.findAll");
            lista = (List<Estados>) q.getResultList();

            return lista;

        } catch (Exception e) {

            return lista;
        }
    }

    @Override
    public TiposUsuarios traeTipoUsuario1(int i) {
        TiposUsuarios t = new TiposUsuarios();
        try {

            Query q = this.em.createNamedQuery("TiposUsuarios.findByIdTipoUsuario");
            q.setParameter("idTipoUsuario", i);
            t = (TiposUsuarios) q.getSingleResult();

            return t;
        } catch (Exception e) {

            return t;
        }
    }

    @Override
    public Dependencias traeDependencia2(int i) {
        Dependencias de = new Dependencias();
        try {

            Query q = this.em.createNamedQuery("Dependencias.findByIdDependencia");
            q.setParameter("idDependencia", i);
            de = (Dependencias) q.getSingleResult();

            return de;
        } catch (Exception e) {

            return de;
        }
    }

    @Override
    public boolean updateDocumento(Documentos docActuali) {
        try {


            this.em.merge(docActuali);
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }

    }

    @Override
    public UnidadesEjecutoras traeUnidadEjecutora(int i) {
        UnidadesEjecutoras unidadEje = new UnidadesEjecutoras();
        try {

            Query q = this.em.createNamedQuery("UnidadesEjecutoras.findByIdUndEjecutora");
            q.setParameter("idUndEjecutora", i);
            unidadEje = (UnidadesEjecutoras) q.getSingleResult();

            return unidadEje;
        } catch (Exception e) {

            return unidadEje;

        }
    }

    @Override
    public List<UnidadesEjecutoras> traeUnidadesEje() {
        List<UnidadesEjecutoras> listaUnidadesEje = new ArrayList<UnidadesEjecutoras>();
        try {

            Query q = this.em.createNamedQuery("UnidadesEjecutoras.findAll");
            listaUnidadesEje = (List<UnidadesEjecutoras>) q.getResultList();

            return listaUnidadesEje;
        } catch (Exception e) {

            return listaUnidadesEje;

        }
    }

    @Override
    public int traeUltimoIdTecnico() {
        int i = 0;
        try {

            Query q = this.em.createNamedQuery("Tecnicos.findIdMayor");
            i = Integer.parseInt(String.valueOf(q.getSingleResult()));
            i++;


            return i;
        } catch (Exception e) {
            if (i == 0) {
                i++;
            }

            return i;
        }
    }

    @Override
    public Roles traeRol(int i) {
        Roles r = new Roles();
        try {

            Query q = this.em.createNamedQuery("Roles.findByIdRol");
            q.setParameter("idRol", i);
            r = (Roles) q.getSingleResult();

            return r;


        } catch (Exception e) {

            return r;
        }
    }

    @Override
    public List<Documentos> traeDocumentos() {
        List<Documentos> listDocumentos = new ArrayList<Documentos>();
        try {

            Query qe = this.em.createNamedQuery("Documentos.findAll");
            listDocumentos = (List<Documentos>) qe.getResultList();

            return listDocumentos;
        } catch (Exception ex) {
            System.out.println(ex.toString());

            return listDocumentos;
        }
    }

    @Override
    public boolean insertCategoriaServicio(CategoriaServicios cateServ) {
        try {


            this.em.persist(cateServ);
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;

        }
    }

    @Override
    public boolean updateCategoriaServicio(CategoriaServicios cateServ) {
        try {


            this.em.merge(cateServ);
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }

    }

    @Override
    public List<CategoriaServicios> traeCategoriaServicios(AppSession appSession) {
        List<CategoriaServicios> listCategoriaServi = new ArrayList<CategoriaServicios>();
        try {

            Query qe = this.em.createNamedQuery("CategoriaServicios.findByIdUndEjecutora").setParameter("idUndEjecutora", appSession.getUsuario().getIdUnidadEjecutora().getIdUndEjecutora());
            listCategoriaServi = (List<CategoriaServicios>) qe.getResultList();

            return listCategoriaServi;
        } catch (Exception ex) {
            System.out.println(ex.toString());

            return listCategoriaServi;
        }
    }

    /**
     *
     * @hecho para controladorServicios
     */
    @Override
    public boolean insertServicios(Servicios serv) {
        try {


            this.em.persist(serv);
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;

        }
    }

    @Override
    public List<Servicios> traeServicios(AppSession appSession) {
        System.out.println(appSession.getUsuario().getIdUnidadEjecutora().getIdUndEjecutora());
        List<Servicios> listServi = new ArrayList<Servicios>();
        try {

            Query qe = this.em.createNamedQuery("Servicios.findByUnidad").setParameter("idUnidadEjecutora", appSession.getUsuario().getIdUnidadEjecutora().getIdUndEjecutora());
            listServi = (List<Servicios>) qe.getResultList();

            return listServi;
        } catch (Exception ex) {
            System.out.println(ex.toString());

            return listServi;
        }
    }

    

    
    @Override
    public boolean updateServicio(Servicios serv) {
        try {


            this.em.merge(serv);
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }

    }

    @Override
    public boolean insertDependencias(Dependencias dep) {
        try {
            this.em.persist(dep);
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
    }

    @Override
    public boolean updateDependencias(Dependencias dep) {
        try {
            this.em.merge(dep);
            return true;
        } catch (Exception e) {

            System.out.println(e.toString());
            return false;
        }

    }

    @Override
    public List<Dependencias> traeDependencias() {
        List<Dependencias> listDependencias = new ArrayList<Dependencias>();
        try {

            Query qe = this.em.createNamedQuery("Dependencias.findAll");
            listDependencias = (List<Dependencias>) qe.getResultList();

            return listDependencias;
        } catch (Exception ex) {
            System.out.println(ex.toString());

            return listDependencias;
        }
    }

    @Override
    public boolean insertDetaRequerimiento(DetaRequerimientos detaReq) {
        try {

            this.em.persist(detaReq);
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
    }

    @Override
    public boolean updateDetaRequerimiento(DetaRequerimientos detaRe) {
        try {

            this.em.merge(detaRe);
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }

    }

    @Override
    public List<DetaRequerimientos> traeDetaRequerimientos() {
        List<DetaRequerimientos> listDetaRequeri = new ArrayList<DetaRequerimientos>();
        try {

            Query qe = this.em.createNamedQuery("DetaRequerimientos.findAll");
            listDetaRequeri = (List<DetaRequerimientos>) qe.getResultList();

            return listDetaRequeri;
        } catch (Exception ex) {
            System.out.println(ex.toString());

            return listDetaRequeri;
        }
    }

    @Override
    public List<DetaRequerimientos> traeDetaRequerimientosDeUnEncaReque(Integer i) {
        List<DetaRequerimientos> listDetaRequeri = new ArrayList<DetaRequerimientos>();
        try {

            Query qe = this.em.createNamedQuery("DetaRequerimientos.findByEncaRequeri");
            qe.setParameter("idEncaRe", i);
            listDetaRequeri = (List<DetaRequerimientos>) qe.getResultList();

            return listDetaRequeri;
        } catch (Exception ex) {
            System.out.println(ex.toString());

            return listDetaRequeri;
        }
    }

    @Override
    public boolean insertDireccionInter(DireccionesInter direInter) {
        try {
            this.em.persist(direInter);
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }

    }

    @Override
    public boolean updateDireccionInter(DireccionesInter direcInter) {
        try {
            this.em.merge(direcInter);
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }

    }

    @Override
    public List<DireccionesInter> traeDireccionesInter() {
        List<DireccionesInter> listDireccionesInters = new ArrayList<DireccionesInter>();
        try {

            Query qe = this.em.createNamedQuery("DireccionesInter.findAll");
            listDireccionesInters = (List<DireccionesInter>) qe.getResultList();

            return listDireccionesInters;
        } catch (Exception ex) {
            System.out.println(ex.toString());

            return listDireccionesInters;
        }
    }

    @Override
    public int traeUltimoIdDireNac() {
        int j = 0;
        try {
            Query q = this.em.createNamedQuery("DireccionesNacionales.findIdMax");
            j = Integer.parseInt(String.valueOf(q.getSingleResult()));
            j++;
            return j;
        } catch (Exception e) {
            if (j == 0) {
                j++;
            }
            return j;
        }
    }

    @Override
    public boolean insertDireccionesNacionales(DireccionesNacionales direcNacionales) {
        try {
            direcNacionales.setIdDirNacional(traeUltimoIdDireNac());
            System.out.println(direcNacionales);
            
            this.em.persist(direcNacionales);
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }

    }

    @Override
    public boolean updateDireccionesNacionales(DireccionesNacionales direcNacionales) {
        try {
            this.em.merge(direcNacionales);
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }

    }

    @Override
    public List<DireccionesNacionales> traeDireccionesNacionales() {
        List<DireccionesNacionales> listDireccionesNaciona = new ArrayList<DireccionesNacionales>();
        try {

            Query qe = this.em.createNamedQuery("DireccionesNacionales.findAll");
            listDireccionesNaciona = (List<DireccionesNacionales>) qe.getResultList();

            return listDireccionesNaciona;
        } catch (Exception ex) {
            System.out.println(ex.toString());

            return listDireccionesNaciona;
        }
    }

    @Override
    public void updateEncaRequerimiento(EncaRequerimientos encaR, List<Documentos> listaD) {
        try {
            this.em.merge(encaR);
        } catch (Exception e) {
        }
    }

    @Override
    public boolean insertEncaRequerimientos(EncaRequerimientos encaR, List<Documentos> listaD, int idUni) {
        Integer nuevo=0;
        try {
            encaR.setNumCaso(encuentraCorrel(idUni));    
            this.em.persist(encaR);
            nuevo=encaR.getNumCaso();
            
            System.out.println("despues de persistir correl "+nuevo +" id und "+ idUni);
                        
            String consulta = "UPDATE unidades_ejecutoras SET correl_caso_und_eje=?2 WHERE id_und_ejecutora= ?1 ";
            Query qconsul = em.createNativeQuery(consulta).setParameter(1,idUni).setParameter(2, nuevo);
            Integer resul = qconsul.executeUpdate();        
            System.out.println("despues de actualizar correl "+nuevo +" resul "+ resul);
            
            List<EncaRequerimientos> listEnca = new ArrayList<EncaRequerimientos>();
            
            for (Documentos documentoDelReque : listaD) {
                documentoDelReque.setIdEncaRequerimiento(encaR);
                this.em.persist(documentoDelReque);
            }

            return true;
        } catch (Exception e) {

            System.out.println(e.toString());
            return false;
        }
        
    }

    @Override
    public void eliminarEncaRequerimiento(int id) {
        try {

            Query q = em.createNamedQuery("EncaRequerimientos.findByIdEncaRequerimiento");
            q.setParameter("idEncaRequerimiento", id);
            EncaRequerimientos e = (EncaRequerimientos) q.getSingleResult();

            em.remove(e);
        } catch (Exception e) {
        }
    }

    @Override
    public int traeId(List<EncaRequerimientos> listEnca) {
        return listEnca.get(listEnca.size() - 1).getIdEncaRequerimiento();
    }

    @Override
    public int devuId(List<Documentos> listDoc) {
        return listDoc.get(listDoc.size() - 1).getIdDocumento();
    }

    @Override
    public void TransFerFile(String fileName, InputStream in, String detino, int id, int tamano) {

        String url = detino + "\\" + traeDependencia(id) + "\\" + fileName;
        try {
            OutputStream out = new FileOutputStream(new File(detino + "\\" + traeDependencia(id) + "\\" + fileName));
            int reader = 0;
            byte[] bytes = new byte[(int) tamano];
            while ((reader = in.read(bytes)) != -1) {
                out.write(bytes, 0, reader);
            }
            in.close();
            out.flush();
            out.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public boolean updateEncaRequerimientos(EncaRequerimientos encaR) {
        try {

            this.em.merge(encaR);
            return true;
        } catch (Exception e) {

            System.out.println(e.toString());
            return false;
        }

    }

    
       
    
    
    @Override
    public void eliminarAsinaTecnico(int i) {
        try {

            Query q = this.em.createNativeQuery("delete from asignar_tecnico where id_enca_requerimiento=" + i);
            q.executeUpdate();

        } catch (Exception e) {
        }
    }

    @Override
    public List<EncaRequerimientos> traeRequrimientosDelTecnico(Integer idTecnico, Integer idUnidadEj) {
        List<EncaRequerimientos> listAsignarTec = new ArrayList<EncaRequerimientos>();
        try {

            Query q = this.em.createNamedQuery("EncaRequerimientos.findByLosDelTecnico");
            q.setParameter("idTec", idTecnico);
            q.setParameter("idUniEje", idUnidadEj);
            listAsignarTec = (List<EncaRequerimientos>) q.getResultList();

            return listAsignarTec;

        } catch (Exception e) {

            return listAsignarTec;
        }

    }

    
    
    @Override
    public List<EncaRequerimientos> traeTodosRequeDelTecnico(Integer idTecnico, Integer idUnidadEj) {
        List<EncaRequerimientos> listaRequeTec = new ArrayList<EncaRequerimientos>();
        try {

            Query q = this.em.createNamedQuery("EncaRequerimientos.findByTodosLosDelTecnico");
            q.setParameter("idTec", idTecnico);
            q.setParameter("idUniEje", idUnidadEj);
            listaRequeTec = (List<EncaRequerimientos>) q.getResultList();

            return listaRequeTec;

        } catch (Exception e) {

            return listaRequeTec;
        }

    }
    
    
    
    
    @Override
    public List<EncaRequerimientos> traeEncaRequerimientoAModificar(int idU) {
        List<EncaRequerimientos> lista = new ArrayList<EncaRequerimientos>();
        try {

            Query q = this.em.createNamedQuery("EncaRequerimientos.findByEstado1");
            q.setParameter("idUnidadEje", idU);
            lista = (List<EncaRequerimientos>) q.getResultList();

            return lista;
        } catch (Exception e) {

            return lista;
        }
    }

    @Override
    public List<EncaRequerimientos> traeEncaRequrimientos(int i, int j, int h) {
        List<EncaRequerimientos> listEncaReq = new ArrayList<EncaRequerimientos>();
        try {

            Query qe = this.em.createNamedQuery("EncaRequerimientos.findByEstado");
            qe.setParameter("idEstado", i);
            qe.setParameter("id", h);
            qe.setParameter("idEstado2", j);

            listEncaReq = (List<EncaRequerimientos>) qe.getResultList();

            return listEncaReq;
        } catch (Exception ex) {
            System.out.println(ex.toString());

            return listEncaReq;
        }
    }

    @Override
    public List<EncaRequerimientos> traeRequeParaVerificar(int iEst, int iUnd) {
        List<EncaRequerimientos> listEncaRequ = new ArrayList<EncaRequerimientos>();
        try {

            Query qe = this.em.createNamedQuery("EncaRequerimientos.findByEstado3");
            qe.setParameter("idEstado", iEst);
            qe.setParameter("iUnd", iUnd);
            listEncaRequ = (List<EncaRequerimientos>) qe.getResultList();

            return listEncaRequ;
        } catch (Exception ex) {
            System.out.println(ex.toString());

            return listEncaRequ;
        }
    }

    @Override
    public int traeUltimoIdEstados() {
        int j = 0;
        try {

            Query q = this.em.createNamedQuery("Estados.findMaxId");
            j = Integer.parseInt(String.valueOf(q.getSingleResult()));
            j++;
            return j;
        } catch (Exception e) {
            if (j == 0) {
                j++;
            }
            return j;
        }
    }

    @Override
    public boolean insertEstados(Estados estado) {
        try {

            estado.setIdEstado(traeUltimoIdEstados());
            this.em.persist(estado);

            return true;
        } catch (Exception e) {

            System.out.println(e.toString());
            return false;
        }
    }

    @Override
    public boolean updateEstados(Estados estado) {
        try {

            this.em.merge(estado);

            return true;
        } catch (Exception e) {

            System.out.println(e.toString());
            return false;
        }

    }

    @Override
    public int traeUltimoId() {
        int i = 0;
        try {
            Query q = this.em.createNamedQuery("Roles.findIdMayor");
            i = Integer.parseInt(String.valueOf(q.getSingleResult()));
            i++;
            return i;
        } catch (Exception e) {
            if (i == 0) {
                i++;
            }
            return i;
        }
    }

    @Override
    public boolean insertRoles(Roles rol) {
        try {

            rol.setIdRol(traeUltimoId());
            this.em.persist(rol);

            return true;
        } catch (Exception e) {

            System.out.println(e.toString());
            return false;
        }
    }

    @Override
    public boolean updateRoles(Roles rol) {
        try {

            this.em.merge(rol);

            return true;
        } catch (Exception e) {

            System.out.println(e.toString());
            return false;
        }

    }

    @Override
    public boolean insertServicio(Servicios servi) {
        try {

            this.em.persist(servi);

            return true;
        } catch (Exception e) {

            System.out.println(e.toString());
            return false;
        }
    }

    @Override
    public boolean updateServicios(Servicios servi) {
        try {

            this.em.merge(servi);

            return true;
        } catch (Exception e) {

            System.out.println(e.toString());
            return false;
        }

    }

    @Override
    public List<Servicios> traeServicios(int i) {
        List<Servicios> listServi = new ArrayList<Servicios>();
        try {

            Query qe = this.em.createNamedQuery("Servicios.findAll");
            qe.setParameter("idUnidadEjecutora", i);
            listServi = (List<Servicios>) qe.getResultList();

            return listServi;
        } catch (Exception ex) {
            System.out.println(ex.toString());

            return listServi;
        }
    }

    @Override
    public boolean insertTecnico(Tecnicos tec) {
        try {

            this.em.merge(tec);

            return true;
        } catch (Exception e) {

            System.out.println(e.toString());
            return false;
        }
    }

    @Override
    public boolean updateTecnico(Tecnicos tecni) {
        try {

            this.em.merge(tecni);

            return true;
        } catch (Exception e) {

            System.out.println(e.toString());
            return false;
        }

    }

    @Override
    public List<Tecnicos> traeTecnic(AppSession appSession) {
        //System.out.println(appSession.getUsuario().getIdUnidadEjecutora().getIdUndEjecutora());
        List<Tecnicos> listTec = new ArrayList<Tecnicos>();
        try {

            Query qe = this.em.createNamedQuery("Tecnicos.findByIdUndEjecutora").setParameter("idUndEjecutora", appSession.getUsuario().getIdUnidadEjecutora().getIdUndEjecutora());
            listTec = (List<Tecnicos>) qe.getResultList();

            return listTec;
        } catch (Exception ex) {
            System.out.println(ex.toString());

            return listTec;
        }
    }
    
    
    @Override
    public List<Usuarios> traeTecnicosActivos(int idu) {
        List<Usuarios> listTecnicosActi = new ArrayList<Usuarios>();
        try {

            Query qe = this.em.createNamedQuery("Usuarios.findTecnicoActive");
            qe.setParameter("idu", idu);
            qe.setParameter("estado", true);
            listTecnicosActi = (List<Usuarios>) qe.getResultList();

            return listTecnicosActi;
        } catch (Exception ex) {
            System.out.println(ex.toString());

            return listTecnicosActi;
        }
    }
    
    
    @Override
    public List<Tecnicos> traeTecnicos(int idUni) {
        List<Tecnicos> listTecnicos = new ArrayList<Tecnicos>();
        try {

            Query qe = this.em.createNamedQuery("Tecnicos.findAll");
            qe.setParameter("idUni", idUni);
            listTecnicos = (List<Tecnicos>) qe.getResultList();

            return listTecnicos;
        } catch (Exception ex) {
            System.out.println(ex.toString());

            return listTecnicos;
        }
    }

    @Override
    public Tecnicos traeTecnico(int i) {
        Tecnicos t = new Tecnicos();
        try {

            Query q = this.em.createNamedQuery("Tecnicos.findByIdTecnico");
            q.setParameter("idTecnico", i);
            t = (Tecnicos) q.getSingleResult();

            return t;
        } catch (Exception e) {

            return t;
        }
    }

    @Override
    public Tecnicos traeTecnico(String usua) {
        Tecnicos tec = new Tecnicos();
        try {

            Query q = this.em.createNamedQuery("Tecnicos.findByUsuarioUsuario");
            q.setParameter("Usuario", usua);
            tec = (Tecnicos) q.getSingleResult();

            return tec;
        } catch (Exception e) {

            return tec;
        }

    }

    @Override
    public int existeTecnico(String usua) {
        int i = 0;
        Tecnicos tec;
        try {

            Query q = this.em.createNamedQuery("Tecnicos.findByUsuarioUsuario");
            q.setParameter("Usuario", usua);
            tec = (Tecnicos) q.getSingleResult();

            return i = 1;
        } catch (Exception e) {

            return i;
        }
    }

    @Override
    public int traeUltimoIdTipoUsuario() {
        int j = 0;
        try {
            Query q = this.em.createNamedQuery("TiposUsuarios.findIdUltimoTipoUsu");
            j = Integer.parseInt(String.valueOf(q.getSingleResult()));
            j++;
            return j;
        } catch (Exception e) {
            if (j == 0) {
                j++;
            }
            return j;
        }
    }

    @Override
    public boolean insertTipoUsuario(TiposUsuarios tipoUsu) {
        try {

            tipoUsu.setIdTipoUsuario(traeUltimoIdTipoUsuario());
            this.em.persist(tipoUsu);

            return true;
        } catch (Exception e) {

            System.out.println(e.toString());
            return false;
        }

    }

    @Override
    public boolean updateTipoUsuario(TiposUsuarios tipoUsu) {
        try {

            this.em.merge(tipoUsu);

            return true;
        } catch (Exception e) {

            System.out.println(e.toString());
            return false;
        }

    }

    @Override
    public int traeIltimoIdDeUnidadEje() {
        int i = 0;
        try {
            Query q = this.em.createNamedQuery("UnidadesEjecutoras.findMaxIdUnidadEjec");
            i = Integer.parseInt(String.valueOf(q.getSingleResult()));
            i++;
            return i;
        } catch (Exception e) {
            if (i == 0) {
                i++;
            }
            return i;
        }
    }

    @Override
    public boolean insertUnidadEjecutora(UnidadesEjecutoras unidadEjecu) {
        try {

            unidadEjecu.setIdUndEjecutora(traeIltimoIdDeUnidadEje());
            this.em.persist(unidadEjecu);

            return true;
        } catch (Exception e) {

            System.out.println(e.toString());
            return false;
        }

    }

    @Override
    public boolean updateUnidadEjecutora(UnidadesEjecutoras unidadEje) {
        try {

            this.em.merge(unidadEje);

            return true;
        } catch (Exception e) {


            System.out.println(e.toString());
            return false;
        }

    }

    @Override
    public int traeUltimoIdUsuario() {
        int j = 0;
        try {
            //
            Query q = this.em.createNamedQuery("Usuarios.findByIdMayorr");
            j = Integer.parseInt(String.valueOf(q.getSingleResult()));
            j++;
            // 
            return j;
        } catch (Exception e) {
            if (j == 0) {
                j++;
            }
            //
            return j;
        }
    }

    @Override
    public boolean insertUsuario(Usuarios usua) {
        try {


            usua.setIdUsuario(traeUltimoIdUsuario());
            this.em.persist(usua);


            return true;
        } catch (Exception e) {


            System.out.println(e.toString());
            return false;
        }

    }

    @Override
    public void operacionTecnicoYUsuario(Usuarios u, Tecnicos t) {

        try {

            this.em.merge(u);
            this.em.merge(t);

        } catch (Exception e) {
        }
    }

    @Override
    public boolean updateUsuario(Usuarios usuario) {
        try {


            this.em.merge(usuario);


            return true;
        } catch (Exception e) {


            System.out.println(e.toString());
            return false;
        }

    }

    @Override
    public List<Usuarios> traeUsuariosTecnicos(int i) {
        List<Usuarios> listUsuarios = new ArrayList<Usuarios>();
        try {

            Query q = this.em.createNamedQuery("Usuarios.findTecnico");
            q.setParameter("idu", i);
            listUsuarios = (List<Usuarios>) q.getResultList();

            return listUsuarios;
        } catch (Exception e) {

            return listUsuarios;

        }
    }

    @Override
    public List<Usuarios> traeUsuariosActivosDeUnidadEjecutora(int i) {
        List<Usuarios> listUsuarios = new ArrayList<Usuarios>();
        try {

            Query qe = this.em.createNamedQuery("Usuarios.findAllAtive");
            qe.setParameter("idu", i);
            qe.setParameter("estado", true);
            listUsuarios = (List<Usuarios>) qe.getResultList();

            return listUsuarios;
        } catch (Exception ex) {
            System.out.println(ex.toString());

            return listUsuarios;
        }
    }

    @Override
    public List<Usuarios> traeUsuarios(int i) {
        List<Usuarios> listUsuarios = new ArrayList<Usuarios>();
        try {

            Query qe = this.em.createNamedQuery("Usuarios.findAll");
            qe.setParameter("idu", i);
            listUsuarios = (List<Usuarios>) qe.getResultList();

            return listUsuarios;
        } catch (Exception ex) {
            System.out.println(ex.toString());

            return listUsuarios;
        }
    }

    @Override
    public boolean insertViaSolicitudes(ViaSolicitudes viaSolicitudes) {
        try {

            this.em.persist(viaSolicitudes);

            return true;
        } catch (Exception e) {

            System.out.println(e.toString());
            return false;
        }

    }

    @Override
    public boolean updateViaSolicitudes(ViaSolicitudes viaSolicitudes) {
        try {

            this.em.merge(viaSolicitudes);

            return true;
        } catch (Exception e) {

            System.out.println(e.toString());
            return false;
        }

    }

    @Override
    public List<ViaSolicitudes> traeViaSolicitudes() {
        List<ViaSolicitudes> listViaSoli = new ArrayList<ViaSolicitudes>();
        try {

            Query qe = this.em.createNamedQuery("ViaSolicitudes.findAll");
            listViaSoli = (List<ViaSolicitudes>) qe.getResultList();

            return listViaSoli;
        } catch (Exception ex) {
            System.out.println(ex.toString());

            return listViaSoli;
        }
    }
    

    @Override
    public boolean insertAsignarTecnico(AsignarTecnico asigTecni) {
        try {

            this.em.merge(asigTecni);

            return true;
        } catch (Exception e) {

            System.out.println(e.toString());
            return false;
        }

    }
    
  
      @Override
    public Integer insertAsignarTecnico2(Integer idEnc, Integer idTec, Integer idUnE, Integer tecActual) {
          Integer resul=null;
        try {
            //System.out.println("dentro de dao " + idEnc + idTec + idUnE);
            String consulta = "UPDATE asignar_tecnico SET id_tecnico= ?2 WHERE id_enca_requerimiento= ?1 AND id_tecnico= ?4 AND id_und_ejecutora= ?3";
            Query qconsul = em.createNativeQuery(consulta).setParameter(1,idEnc).setParameter(2, idTec).setParameter(3, idUnE).setParameter(4, tecActual);
            resul =qconsul.executeUpdate();
            
        } catch (Exception e) {

            System.out.println(e.toString());
          
        }
            return resul;

    }
    
    

    @Override
    public AsignarTecnico encontrarAsignaTecnico(int idRequeri, int idTec, int idUniEje) {
        AsignarTecnico asigTec = new AsignarTecnico();
        try {

            Query q = this.em.createNamedQuery("AsignarTecnico.findByPk");
            q.setParameter("idEncaRequeri", idRequeri);
            q.setParameter("idTec", idTec);
            q.setParameter("idUnidadEje", idUniEje);
            asigTec = (AsignarTecnico) q.getSingleResult();

            return asigTec;
        } catch (Exception e) {

            return asigTec;
        }
    }

    @Override
    public boolean updateAsignarTecnico(AsignarTecnico asigTecni) {
        try {

            this.em.merge(asigTecni);

            return true;
        } catch (Exception e) {


            System.out.println(e.toString());
            return false;
        }

    }

    @Override
    public Integer encontrarNumCaso(int i) {
        Integer k = -1;
        try {

            Query q = this.em.createNamedQuery("AsignarTecnico.numeroDeCasosDeUnidadEjecutora");
            q.setParameter("idUnidadEjecutora", i);
            k = Integer.parseInt(q.getSingleResult().toString());

            return k;
        } catch (Exception e) {

            return k;
        }
    }

    @Override
    public Integer encontrarNumCaso2(int i) {
        Integer j = 0;
        try {

            Query q = this.em.createNamedQuery("EncaRequerimientos.findByNumeroDeCaso2");
            q.setParameter("idunide", i);
            j = Integer.parseInt(q.getSingleResult().toString());
            j = j++;
            return j;
        } catch (Exception e) {
            if (j == 0) {
                j++;

            }
            return j;
        }

    }

    
        //Encontrar el correlativo del número de caso de la Unidad Ejecutora
    @Override
    public Integer encuentraCorrel(int i){
    Integer correlUnd=0;
    
        try{
            String consulta = "SELECT correl_caso_und_eje FROM unidades_ejecutoras WHERE id_und_ejecutora= ?1";
            Query qconsul;
            qconsul = em.createNativeQuery(consulta).setParameter(1,i);
            
            correlUnd = (Integer) qconsul.getSingleResult();  // Linea nueva. Cast a integer de una sola vez
                       
            correlUnd=correlUnd+1;
            //System.out.println("correlativo que lleva despues de aumentar "+correlUnd);
        } catch(Exception e){
            //System.out.println("Error: " + e.getMessage());
            if (correlUnd==0){
                correlUnd++;
            }
        }
        
        return correlUnd;
        
    }
    
    
    
    
    //Actualizar el nuevo correlativo del numero de caso de la Unidad Ejecutora
    @Override
    public Integer actualizaCorrel(Integer i, Integer nuevoCorrel){
    Integer resul=null;
        try{
            System.out.println("nuevo correlativo que lleva 3 "+nuevoCorrel);
            String consulta = "UPDATE unidades_ejecutoras SET correl_caso_und_eje=?2 WHERE id_und_ejecutora= ?1 ";
            Query qconsul = em.createNativeQuery(consulta).setParameter(1,i).setParameter(2, nuevoCorrel);
            resul =qconsul.executeUpdate();
            
            } catch(Exception e){
        
        }
        
        return resul;
    }
    
    
        
    @Override
    public Integer encontrarTecnico(Integer i) {
        Tecnicos tecnico = new Tecnicos();
        try {
            System.out.println("tecnico");
            System.out.println(i);
            Query q = this.em.createNamedQuery("Tecnicos.findByIdTecnico");
            q.setParameter("idTecnico", i);
            tecnico = (Tecnicos) q.getSingleResult();

            return tecnico.getUnidadesEjecutoras().getIdUndEjecutora();
        } catch (Exception e) {

            return tecnico.getUnidadesEjecutoras().getIdUndEjecutora();

        }
    }

    @Override
    public String traeDependencia(int idUsus) {
        Usuarios depend;
        try {
            //
            Query q = this.em.createNamedQuery("Usuarios.findByIdUsuario");
            q.setParameter("idUsuario", idUsus);
            depend = (Usuarios) q.getSingleResult();
            // 
            return depend.getIdDependencia().getNombreDependencia();
        } catch (Exception e) {
            // 
            return "fallo";

        }

    }

    @Override
    public List<Object[]> traeListaDeRequeriYAsigna(int idUni) {
        List<Object[]> asi = new ArrayList<Object[]>();
        try {

            Query q = this.em.createNamedQuery("EncaRequerimientos.findBytodo");
            q.setParameter("idu", idUni);
            asi = q.getResultList();

            return asi;
        } catch (Exception e) {

            return asi;

        }
    }

    @Override
    public List<SumaPesoTecnico> traeTecnicosConSuPeso(int valor) {
        List<SumaPesoTecnico> listTecPeso = new ArrayList<SumaPesoTecnico>();
        try {

            Query q = this.em.createNativeQuery("select * from( select tecnico.idTecn,tecnico.nomtec, SUM(te.pes) as summa, tecnico.nomunidad as unidad_ejecutora from( select t.id_tecnico as idTecn, t.nombre_tecnico as nomtec,unidades.nombre_und_ejecutora as nomunidad from usuarios as u, tecnicos as t, unidades_ejecutoras as unidades where u.usuario_usuario=t.usuario_usuario and u.id_und_ejecutora=unidades.id_und_ejecutora and u.id_und_ejecutora=" + valor + " and u.id_rol=2 and u.estado=true) as tecnico  left join( select astec.id_tecnico as id, astec.peso as pes from enca_requerimientos as enca, asignar_tecnico as astec where enca.id_enca_requerimiento=astec.id_enca_requerimiento and  enca.id_estado=2) as te on (te.id=tecnico.idTecn) group by tecnico.idTecn,tecnico.nomtec,tecnico.nomunidad)as ta");

            //Query q =this.em.createNativeQuery("select ta.idTecn, ta.nomtec,ta.summa,unidades_ejecutoras.nombre_und_ejecutora from ( select tecnicos.id_tecnico as idTecn, tecnicos.nombre_tecnico as nomtec,SUM(t.pes)as summa,tecnicos.id_und_ejecutora as idje	from	tecnicos	left join (	select astec.id_tecnico as id,astec.peso as pes	from enca_requerimientos as enca,asignar_tecnico as astec	where enca.id_enca_requerimiento=astec.id_enca_requerimiento and enca.id_estado=2	)as t	on (tecnicos.id_tecnico=t.id)	group by tecnicos.id_tecnico,tecnicos.nombre_tecnico,tecnicos.id_und_ejecutora	)as ta,unidades_ejecutoras	where ta.idje=unidades_ejecutoras.id_und_ejecutora and unidades_ejecutoras.id_und_ejecutora="+valor);
            List<Object[]> lista = q.getResultList();
            for (Object[] objects : lista) {
                SumaPesoTecnico tecnico = new SumaPesoTecnico();
                if (objects[2] == null) {
                    tecnico.setSumaPeso(0);
                } else {
                    tecnico.setSumaPeso(Integer.parseInt(objects[2].toString()));
                }
                tecnico.setIdTecnico(Integer.parseInt(objects[0].toString()));
                tecnico.setNombre(objects[1].toString());
                tecnico.setUnidadEjecutora(objects[3].toString());
                listTecPeso.add(tecnico);

            }

            return listTecPeso;
        } catch (Exception e) {

            return listTecPeso;
        }
    }


    @Override
    public Tecnicos devuelveTecnicos(Integer i) {
        Tecnicos t = new Tecnicos();

        try {

            Query q = this.em.createNamedQuery("Tecnicos.findByIdTecnico");
            q.setParameter("idTecnico", i);
            t = (Tecnicos) q.getSingleResult();
            return t;

        } catch (Exception e) {
            System.out.println(e.toString());

            return t;

        }
    }

    @Override
    public AsignarTecnico traeAsignarTecnicoEncaRequeri(Integer i, String usuarioLogeado) {
        AsignarTecnico t = new AsignarTecnico();
        Tecnicos tecnico = new Tecnicos();
        try {

            Query qTec = this.em.createNamedQuery("Tecnicos.findByUsuarioUsuario");
            qTec.setParameter("Usuario", usuarioLogeado);
            tecnico = (Tecnicos) qTec.getSingleResult();
            Query q = this.em.createNamedQuery("AsignarTecnico.findByPk");
            q.setParameter("idEncaRequeri", i);
            q.setParameter("idTec", tecnico.getTecnicosPK().getIdTecnico());
            q.setParameter("idUnidadEje", tecnico.getTecnicosPK().getIdUndEjecutora());

            t = (AsignarTecnico) q.getSingleResult();
            return t;
        } catch (Exception e) {

            return t;
        }
    }

    @Override
    public int traeUltimoDocumento() {
        int id = -1;
        try {


            Query q = this.em.createNativeQuery("SELECT d.id_documento FROM documentos as d WHERE id_documento = (SELECT MAX(id_documento) FROM documentos)");
            id = Integer.parseInt(q.getSingleResult().toString());


            return id;
        } catch (Exception e) {

            return id;
        }
    }

    @Override
    public boolean traeEstadoPorElTecnico(int idEnca, int idTec, int idUnidadEje) {
        AsignarTecnico asigTec = new AsignarTecnico();
        try {

            Query q = this.em.createNamedQuery("AsignarTecnico.findByPk");
            q.setParameter("idEncaRequeri", idEnca);
            q.setParameter("idTec", idTec);
            q.setParameter("idUnidadEje", idUnidadEje);
            asigTec = (AsignarTecnico) q.getSingleResult();

            if (asigTec.getEstadoDelTecnico().equals("pausado")) {
                return true;

            } else {
                return false;
            }
        } catch (Exception e) {

            return false;
        }
    }

    @Override
    public List<TiposUsuarios> traeTipoUsuarios() {
        List<TiposUsuarios> listaTipoUsuarios = new ArrayList<TiposUsuarios>();
        try {

            Query q = this.em.createNamedQuery("TiposUsuarios.findAll");
            listaTipoUsuarios = (List<TiposUsuarios>) q.getResultList();

            return listaTipoUsuarios;
        } catch (Exception e) {

            return listaTipoUsuarios;
        }
    }

    @Override
    public Usuarios traeUsuarioLogeado(String nombre) {
        Usuarios usuario = new Usuarios();
        try {

            Query q = this.em.createNamedQuery("Usuarios.findByUsuarioUsuario");
            q.setParameter("usuarioUsuario", nombre);
            usuario = (Usuarios) q.getSingleResult();

            return usuario;
        } catch (Exception e) {

            return usuario;
        }
    }

    @Override
    public List<Roles> traeRoles() {
        List<Roles> listaRoles = new ArrayList<Roles>();
        try {

            Query q = this.em.createNamedQuery("Roles.findAll");
            listaRoles = (List<Roles>) q.getResultList();

            return listaRoles;
        } catch (Exception e) {

            return listaRoles;
        }
    }

    @Override
    public List<Estados> listaEstados() {
        List<Estados> listaEstado = new ArrayList<Estados>();
        try {

            Query q = this.em.createNamedQuery("Estados.findAll");
            listaEstado = (List<Estados>) q.getResultList();

            return listaEstado;
        } catch (Exception e) {

            return listaEstado;
        }
    }

    
    @Override
    public Integer actualizaTecR(Integer idTec2){
        System.out.println(idTec2);
      String actualiza = "UPDATE AsignarTecnico SET idTecnico = ?1";  
      Query q = em.createNativeQuery(actualiza).setParameter(1, idTec2);
      int aTecR = q.executeUpdate();
      return aTecR;
    
    }
    
     @Override
     public Tecnicos tecnicoPorId (int idTecnico){
     return (Tecnicos) this.em.createNamedQuery("Tecnicos.findByIdTecnico").setParameter("idTecnico", idTecnico).getSingleResult();
     }

     @Override
    public List<CategoriaServicios> traeCategoriaServicios(int i) {
        List<CategoriaServicios> listCatServi = new ArrayList<CategoriaServicios>();
        try {

            Query qe = this.em.createNamedQuery("CategoriaServicios.findByIdUndEjecutora");
            qe.setParameter("idUndEjecutora", i);
            listCatServi = (List<CategoriaServicios>) qe.getResultList();

            return listCatServi;
        } catch (Exception ex) {
            System.out.println(ex.toString());

            return listCatServi;
        }
    
    }

    
    
    
}
