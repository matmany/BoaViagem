package boaviagem.casadocodigo.com.br.boaviagem.domain;

import java.util.Date;

/**
 * Created by matmany on 22/04/16.
 */
public class Viagem {

    private Long id;
    private String destino;
    private Integer tipoViagem;
    private Date dataChegada;

    private Date dataSaida;
    private Double orcamento;
    private Integer quantidadePessoas;

    public Viagem()
    {

    }

    public Viagem(Long id, String destino,Integer tipoViagem,
                  Date dataChegada,Date dataSaida,Double orcamento,Integer quantidadePessoas)
    {
        this.id = id;

        this.destino = destino;

        this.tipoViagem = tipoViagem;

        this.dataChegada = dataChegada;

        this.dataSaida = dataSaida;

        this.orcamento = orcamento;

        this.quantidadePessoas = quantidadePessoas;
    }

    public Long get_id()
    {
        return id;
    }

    public Date get_data_chegada()
    {
        return dataChegada;
    }

    public String get_destino()
    {
        return destino;
    }

    public Integer get_Tipoviagem()
    {
        return tipoViagem;
    }

    public Date get_data_saida()
    {
        return dataSaida;
    }


    public Double get_orcamento()
    {
        return orcamento;
    }

    public Integer get_quantidadePessoas()
    {
        return quantidadePessoas;
    }



    /////////////sets

    public void set_id(Long id)
    {
        this.id=id;
    }

    public void set_data_chegada(Date dataChegata)
    {
        this.dataChegada=dataChegata;
    }


    public void set_destino(String destino)
    {
         this.destino=destino;
    }

    public void set_Tipoviagem(Integer tipoViagem)
    {
        this.tipoViagem=tipoViagem;
    }

    public void set_data_saida(Date dataSaida)
    {
        this.dataSaida=dataSaida;
    }


    public void set_orcamento(Double orcamento)
    {
        this.orcamento=orcamento;
    }

    public void set_quantidadePessoas(Integer quantidadePessoas)
    {
        this.quantidadePessoas=quantidadePessoas;
    }
}
