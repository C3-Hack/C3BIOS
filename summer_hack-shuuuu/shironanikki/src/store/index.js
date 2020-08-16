/*jshint esversion: 6 */
/*jshint es5: false */
import Vue from 'vue';
import Vuex from 'vuex';
import firebase from 'firebase';
import router from "@/router";


Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    user: null,
    isAuthenticated: false,
    username: "",
    userid: "",
    profiletext: "",
    contents: [],
    calendar: [],
    cards: [],
  },
  mutations: {
    setUser(state, payload) {
      state.user = payload;
    },
    setIsAuthenticated(state, payload) {
      state.isAuthenticated = payload;
    },
    setUsername(state, payload) {
      state.username = payload;
    },
    setUserID(state, payload) {
      state.userid = payload;
    },
    setProfileText(state, payload) {
      state.profiletext = payload;
    },
    setContents(state, payload) {
      state.contents = payload;
    },
    setCalendar(state, payload){
      state.calendar = payload;
    },
    setCards(state, payload) {
      state.cards = payload;
    }
  },
  actions: {
    userJoin({ commit }, { email, password }) {
      firebase
        .auth()
        .createUserWithEmailAndPassword(email, password)
        .then(
          user => {
            commit('setUser', user);
            commit('setIsAuthenticated', true);
            alert('Success!');
            router.push('/setuser');
          },
          err => {
            alert(err.message);
          }
        )
        .catch(() => {
          commit('setUser', null);
          commit('setIsAuthenticated', false);
          alert('error!!!!');
          router.push('/signup');
        });
    },

    setUserInfo({ state }, { username, userid, profiletext }) {
      firebase
        .database()
        .ref('users/' + state.user.user.uid)
        .set({
          username: username,
          userid: userid,
          profiletext: profiletext
        });
      alert('Success!');
      router.push('/profile');
    },

    getUserInfo({ state, commit }) {
      return firebase
        .database()
        .ref('users/' + state.user.user.uid)
        .once('value', snapshot => {
          commit('setUsername', snapshot.val().username);
          commit('setUserID', snapshot.val().userid);
          commit('setProfileText', snapshot.val().profiletext);
        });
    },

    userLogin({ commit }, { email, password }) {
      firebase
        .auth()
        .signInWithEmailAndPassword(email, password)
        .then(
          user => {
            commit('setUser', user);
            commit('setIsAuthenticated', true);
            router.push('/home');
          },
          err => {
            alert(err.message);
          }
        )
        .catch(() => {
          commit('setUser', null);
          commit('setIsAuthenticated', false);
          router.push('/signin');
        });
    },
    userSignOut({ commit }) {
      firebase
        .auth()
        .signOut()
        .then(() => {
          commit('setUser', null);
          commit('setIsAuthenticated', false);
          commit('setUsername', "");
          commit('setUserID', "");
          commit('setProfileText', "");
          commit('setContents', null);
          commit('setCards', null);
          router.push('/signin');
        })
        .catch(() => {
          commit('setUser', null);
          commit('setIsAuthenticated', false);
          commit('setUsername', "");
          commit('setUserID', "");
          commit('setProfileText', "");
          commit('setContents', null);
          commit('setCards', null);
          router.push('/');
        });
    },

    setContent({ state }, { contentname, color, goaltext }) {
      firebase
        .database()
        .ref('users/' + state.user.user.uid + '/contents')
        .push({
          contentname: contentname,
          color: color,
          goaltext: goaltext
        });
      alert('set content Success!');
      router.push('/profile');
    },

    setCalendar({ state }, { date, color }) {
      firebase
        .database()
        .ref('users/' + state.user.user.uid + '/calendar/'+date)
        .push({
          color: color,
        });
      alert('Success!');
      router.push('/profile');
    },

    setCard({state}, {text, color, date}) {
      firebase
        .database()
        .ref('users/' + state.user.user.uid + '/cards')
        .push({
          text: text,
          color: color,
          date: date,
        });
      firebase
        .database()
        .ref('users/' + state.user.user.uid + '/calendar/' + date)
        .push({
          color: color,
        });
      alert('set card Success!');
    },

    getContents({ state, commit }) {
      return firebase
        .database()
        .ref('users/' + state.user.user.uid + '/contents')
        .once('value', snapshot => {
          commit('setContents', snapshot.val());
        });
    },

    getCalendar({ state, commit }) {
      return firebase
        .database()
        .ref('users/' + state.user.user.uid+'/calendar')
        .once('value', snapshot => {//calendarの後にくる
        commit('setCalendar', snapshot.val());
        });
    },

    getCards({state, commit}) {
      return firebase
        .database()
        .ref('users/' + state.user.user.uid + '/cards')
        .on('value', snapshot => {
          commit('setCards', snapshot.val());
        });
    },
  },
  getters: {
    isAuthenticated(state) {
      return state.user !== null && state.user !== undefined;
    }
  },
  modules: {
  }
});
