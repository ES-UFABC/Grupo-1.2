export const search = {
  namespaced: true,
  state: {
    term: null
  },
  getters: {
    searchTerm(state) {
      return state.term;
    }
  },
  actions: {
    setTerm({ commit }, term) {
      commit('setTerm', term);
    },
    cleanTerm({ commit }) {
      commit('cleanTerm');
    },
  },
  mutations: {
    setTerm(state, term) {
      state.term = term;
    },
    cleanTerm(state) {
      state.term = null;
    },
  }
};
