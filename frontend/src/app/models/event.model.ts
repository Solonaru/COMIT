export interface Event {
    id: number;
    name: string;
    description: string;
    startDate: Date;
    endDate: Date;
    eventType: string;
    tags: string[];
}
