import Cookies from 'js-cookie'

class SearchHistoryService {
  carregarHistoricoDeBuscas() {
    return new Promise(function (resolve, reject) {
      let historico = JSON.parse(Cookies.get(process.env.VUE_APP_LOCAL_SEARCH_HISTORY_KEY) || '{ }')
      if (historico && historico.termos)
        return resolve(historico)
      return resolve({ termos: [] })
    });
  }
  salvarPesquisaNoHistoricoDeBuscas(termo) {
    this.carregarHistoricoDeBuscas().then(historico => {
      if(!historico.termos.includes(termo))
        historico.termos.push(termo)
      Cookies.set(process.env.VUE_APP_LOCAL_SEARCH_HISTORY_KEY, JSON.stringify(historico))
    })

  }

  limparHistoricoDeBuscas() {
    return new Promise(function (resolve, reject) {
      Cookies.remove(process.env.VUE_APP_LOCAL_SEARCH_HISTORY_KEY)
      return resolve(true)
    });
  }
}
export default new SearchHistoryService();
