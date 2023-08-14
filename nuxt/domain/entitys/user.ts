export default class User {
  uuid: string;
  nickname: string;
  iconUrl: string;
  email: string;
  firebaseUid: string;

  constructor(
    uuid: string,
    nickname: string,
    iconUrl: string,
    email: string,
    firebaseUid: string
  ) {
    this.uuid = uuid;
    this.nickname = nickname;
    this.iconUrl = iconUrl;
    this.email = email;
    this.firebaseUid = firebaseUid;
  }
};