export const layout = {
  namespaced: true,
  state: {
    sidebarClose: true,
    sidebarActiveElement: null,
    isLoading: false,
    refCount: 0
  },
  mutations: {
    switchSidebar(state, value) {
      if (value) {
        state.sidebarClose = value;
      } else {
        state.sidebarClose = !state.sidebarClose;
      }
    },
    changeSidebarActive(state, index) {
      state.sidebarActiveElement = index;
    },
    changeLoading(state, isLoading) {
      console.log({ isLoading })
      if (isLoading) {
        state.refCount++;
        state.isLoading = true;
      } else if (state.refCount > 0) {
        state.refCount--;
        state.isLoading = (state.refCount > 0);
      }
    }
  },
  actions: {
    switchSidebar({ commit }, value) {
      commit('switchSidebar', value);
    },
    changeSidebarActive({ commit }, index) {
      commit('changeSidebarActive', index);
    },
    changeLoading({ commit }, isLoading) {
      commit('changeLoading', isLoading);
    },
  },
};
