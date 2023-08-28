export default class Practice {
  uuid: string;
  title: string;
  description: string;
  maxParticipants: number;
  prefectureId: number;
  organizerId: string;
  heldOn: string;
  isJoined: boolean;
  createdAt: string;

  constructor(
    uuid: string, 
    title: string, 
    description: string, 
    maxParticipants: number, 
    prefectureId: number,
    organizerId: string,
    heldOn: string,
    isJoined: boolean,
    createdAt: string
  ) {
    this.uuid = uuid;
    this.title = title;
    this.description = description;
    this.maxParticipants = maxParticipants;
    this.prefectureId = prefectureId;
    this.organizerId = organizerId;
    this.heldOn = heldOn;
    this.isJoined = isJoined;
    this.createdAt = createdAt;
  }
};