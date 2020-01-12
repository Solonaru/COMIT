import { EnumObject } from './enum-object.model';

export class Event {
    id: number;
    name: string;
    description: string;
    startDate: Date;
    endDate: Date;
    eventType: EnumObject;
    technologies: EnumObject[];
    tags: string[];
}
