export default class MolkkyActivity {
  uuid: string;
  content: string;
  posterId: string;
  prefectureId: number;
  createdAt: string;

  constructor(
    uuid: string, 
    content: string, 
    posterId: string,
    prefectureId: number,
    createdAt: string
  ) {
    this.uuid = uuid;
    this.content = content;
    this.posterId = posterId;
    this.prefectureId = prefectureId;
    this.createdAt = createdAt;
  }
};