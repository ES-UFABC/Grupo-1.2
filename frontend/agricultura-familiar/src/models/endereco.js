export default class Endereco {
  constructor(rua, numero, bairro, municipio, estado, cep, complemento) {
    this.cep = cep;
    this.numero = numero;
    this.complemento = complemento;
    this.rua = rua;
    this.bairro = bairro;
    this.municipio = municipio;
    this.estado = estado;
  }
}
