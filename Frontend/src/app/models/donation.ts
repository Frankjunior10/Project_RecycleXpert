export interface DonationDTO {
  id?: number;
  donorName: string;
  amount: number;
  donationDate?: Date | string; // Permitir ambos tipos
  eventId: number;
  volunteerId: number;
}
