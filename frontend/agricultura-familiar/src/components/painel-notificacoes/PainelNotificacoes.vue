<template>
  <div class="page-container">
    <Voltar />
    <h4 class="titulo">Notificações</h4>
    <b-card no-body style="min-width: 100%">
      <b-list-group flush>
        <Notificacao
          v-for="(notificacao, index) in notificacoes"
          :key="index"
          :notificacao="notificacao"
          v-if="notificacoes"
        />
      </b-list-group>
    </b-card>
  </div>
</template>
<script>
import Voltar from "../../components/voltar/Voltar";
import Notificacao from "../../components/notificacao/Notificacao";
import ProdutorService from "../../services/produtor.service";
export default {
  name: "PainelNotificacoes",
  components: { Voltar, Notificacao },
  data() {
    return {
      notificacoes: [],
    };
  },
  mounted: function () {
    let self = this;
    ProdutorService.carregarNotificacoes().then((response) => {
      let notificacoes = response.data.content;
      if (notificacoes && notificacoes.length) self.notificacoes = notificacoes;
    });
  },
};
</script>

<style scoped src="../../styles/painel-notificacoes.css"></style>