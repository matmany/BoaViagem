package boaviagem.casadocodigo.com.br.boaviagem.domain;

import java.util.Date;

/**
 * Created by matmany on 22/04/16.
 */
public class Gasto {

    private Long id;
    private Date data;
    private String categoria;
    private String descricao;
    private Double valor;
    private String local;
    private Integer viagemId;

    public Gasto()
    {}

    public Gasto(Long id, Date data, String categoria,String descricao,
                 Double valor, String local, Integer viagemId  )
    {
        this.id = id;
        this.data=data;
        this.categoria=categoria;
        this.descricao=descricao;
        this.valor=valor;
        this.local=local;
        this.viagemId = viagemId;
    }

    public Long get_id()
    {
        return id;
    }

    public Date get_data()
    {
      return data;
    }

    public String get_categoria()
    {
       return categoria;
    }

    public String get_descricao()
    {
     return descricao;
    }

    public Double get_valor()
    {
      return valor;
    }

    public String get_local()
    {
      return local;
    }

    public Integer get_viagemId()
    {
     return viagemId;
    }

    /////////////sets

    public void set_id(Long id)
    {
        this.id=id;
    }

    public void set_data(Date date)
    {
       this.data=date;
    }

    public void set_categoria(String categoria)
    {
     this.categoria= categoria;
    }

    public void set_descricao(String descricao)
    {
     this.descricao=descricao;
    }

    public void set_valor(Double valor)
    {
     this.valor=valor;
    }

    public void set_local(String local)
    {
     this.local=local;
    }

    public void set_viagemId(Integer viagemId)
    {
        this.viagemId = viagemId;
    }


}
