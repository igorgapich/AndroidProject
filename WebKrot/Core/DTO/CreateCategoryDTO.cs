namespace Core.DTO
{
    public class CreateCategoryDTO
    {
        public required string Name { get; set; }
        public string? Description { get; set; }
        public string? ImagePath { get; set; }
    }
}